package org.onebusaway;

import org.onebusaway.common.impl.UtilityLibrary;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class OfflineTasks implements Runnable {

  public static void main(String[] args) {
    List<String> paths = new ArrayList<String>();
    paths.add("classpath:data-sources-common.xml");
    paths.add("classpath:data-sources-offline.xml");
    paths.add("classpath:org/onebusaway/application-context-common.xml");
    paths.add("classpath:org/onebusaway/application-context-offline.xml");
    ApplicationContext context = UtilityLibrary.createContext(paths);
    OfflineTasks tasks = (OfflineTasks) context.getBean("offlineTasks");
    tasks.run();
  }
  
  private List<Runnable> _tasks = new ArrayList<Runnable>();
  
  public void setTasks(List<Runnable> tasks) {
    _tasks = tasks;
  }

  public void run() {
    for (Runnable task : _tasks) {
      System.out.println("task=" + task);
      task.run();
    }
  }
}