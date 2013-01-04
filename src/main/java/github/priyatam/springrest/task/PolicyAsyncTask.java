package github.priyatam.springrest.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import github.priyatam.springrest.helper.MailHelper;
import github.priyatam.springrest.helper.PersistenceHelper;
import github.priyatam.springrest.helper.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.priyatam.springrest.domain.Policy;

import javax.inject.Inject;

/**
 * Async processing using direct JDK 6 Executor interface
 * 
 * @author pmudivarti
 * 
 */
@Service
public class PolicyAsyncTask {

	final static Logger logger = LoggerFactory.getLogger(PolicyAsyncTask.class);

	@Inject
	ResourceHelper resourceHelper;
	
	@Inject
	MailHelper mailer;

    @Inject
    PersistenceHelper persistence;

	/*
	 * Thread pool Acts as a throttle preventing out of memory exception
	 */
	private ExecutorService executor = new ThreadPoolExecutor(10, 50, Long.MAX_VALUE, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(2000), new ThreadPoolExecutor.DiscardOldestPolicy());

	public void createPolicy(final String futureKey, final Policy policy) {
		logger.debug("Processing Policy creation asynchornously ... ");
		executor.execute(new Runnable() {

			@Override
			public void run() {
				String threadName = Thread.currentThread().getName();
				logger.debug(threadName + " beginning work on create policy ... ");
				logger.debug("Executing Create Policy asynchronously ...");

				String policyNum = null;
				try {
					policyNum = persistence.create(policy);
				} catch (Exception e) {
					logger.error("Unable to save Policy, updating resource status ...");
					resourceHelper.createError(futureKey, e);
				}

				resourceHelper.createPermLocation("/policy", futureKey, policyNum);

				mailer.sendMail("wara-ws-demo@plymouthrock.com", "pmudivarti@plymouthrock.com", "New Policy Created",
						"A new policy has been created at: " + resourceHelper.getPermLocation(futureKey));

				logger.debug(threadName + " completed create policy and updated resource cache.");
			}
		});
	}
}
