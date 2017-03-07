package cn.mopon.cec.task;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory
{

	private static AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean daemon;
 
    /**
     * Constructor accepting the prefix of the threads that will be created by this {@link ThreadFactory}
     * 
     * @param namePrefix
     *            Prefix for names of threads
     */
    public NamedThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
 
    }
    /**
     * Constructor accepting the prefix of the threads that will be created by this {@link ThreadFactory}
     *
     * @param namePrefix
     *            Prefix for names of threads
     */
    public NamedThreadFactory(String namePrefix) {
        this(namePrefix, false);
    }
	@Override
	public Thread newThread(Runnable r)
	{
	    final Thread thread = new Thread(r, namePrefix + " thread-" + threadNumber.getAndIncrement());
	    thread.setDaemon(daemon);
	    return thread;
	}
}
