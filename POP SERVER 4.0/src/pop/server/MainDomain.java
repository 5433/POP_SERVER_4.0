package pop.server;

import java.util.HashMap;

public final class MainDomain{
	
	int i = 8888;
	String mes = "";
	KeywordSearchServer kss;
        ThreadSearcher threadServer;
	static HashMap<Integer,KeywordSearchServer> serverCollector = new HashMap<>();
        static HashMap<Integer,ThreadSearcher> searchServerCollector = new HashMap<>();
	
	public MainDomain(){
		executeService();
        }
        
	   public void executeService() {

               Thread thread = new Thread(new NewRunThread());
               Thread sThread = new Thread(new NewSearchThread());
               thread.start();
               sThread.start();

               while (true) {
                   try {
                       if (KeywordSearchServer.newForum == true) {
                           i++;
                           mes = KeywordSearchServer.temp;
                           System.out.println(mes);
                           KeywordSearchServer kes = new KeywordSearchServer(i, mes.replace("create", ""));
                           serverCollector.put(i,kes);
                       }
                   } catch (NullPointerException e) {
                       // do nothing
                   }
               }
    }
	
	class NewRunThread implements Runnable{
            @Override
            public void run(){                
                serverCollector.put(i, kss);
		kss = new KeywordSearchServer(i," Main");
            }
	}
        
        class NewSearchThread implements Runnable{
            @Override
            public void run(){
                searchServerCollector.put(7777, threadServer);
                threadServer = new ThreadSearcher(7777," Search");                
            }
        }
	
}