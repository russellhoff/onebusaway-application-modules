# Troubleshooting Guide

## Why does searching for an intersection return the same set of results for any address?

First, check that your geocoder implementation is set appropriately. In data-sources.xml, ensure the
"externalGeocoderImpl" line looks like this:

~~~
<bean id="externalGeocoderImpl" class="org.onebusaway.geocoder.impl.GoogleGeocoderImpl" />
~~~

Secondly, flush the geocoder cache. To do that, connect to the OBA database and run:

~~~
delete * from oba_geocoder_results;
~~~

## I'm running OBA in a virtualized environment, and seeing OBA's memory consumption keep going up. What can I do?

On certain virtualized environments, the JDK can have trouble detecting how much memory to allocate to each webapp.
The solution is to set a maximum memory limit by setting the JAVA_OPTS environment variable as so:

~~~
-Xmx256M -server 
~~~

For more information on the specific interaction with OpenVZ and the JDK, see
http://forum.proxmox.com/threads/1495-Proxmox-OpenVZ-memory-Java-VMs-and-Zimbra

## After upgrading from a previous version, hibernate didn't update my DB schema, and now it's complaining that "duplicate key value violates unique constraint 'oba_nyc_raw_location_pkey'"!

The best way to fix this is to either add the changed columns yourself (not recommended) or, your better bet, drop the
database and let hibernate regenerate it all over again. We'd had bad luck dropping individual tables and waiting for
those to be rebuilt. 

If you need to migrate data from a previous schema version, we found this little script handy:

~~~
-- create function to prevent optimizer from removing the select nextval
CREATE FUNCTION volatile_nextval(text) RETURNS bigint VOLATILE AS 'BEGIN RETURN nextval($1); END;' LANGUAGE 'plpgsql';

-- move old data into temp table
select bearing, destinationsigncode, deviceid, gga, latitude, longitude, rmc, time, -1 AS timereceived, vehicle_agencyid, vehicle_id, (select volatile_nextval('hibernate_sequence') AS id) AS id into temporary table test from oba_nyc_raw_location_old;

-- update id value to sequence value (can't do this in a select...)
update test set id=volatile_nextval('hibernate_sequence');

-- move back to real table
insert into oba_nyc_raw_location(bearing, destinationsigncode, deviceid, gga, latitude, longitude, rmc, time, timereceived, vehicle_agencyid, vehicle_id, id) select bearing, destinationsigncode, deviceid, gga, latitude, longitude, rmc, time, -1, vehicle_agencyid, vehicle_id, id from test;
~~~