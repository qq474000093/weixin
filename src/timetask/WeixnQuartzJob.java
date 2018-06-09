package timetask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;



public class WeixnQuartzJob extends QuartzJobBean{
    private RefreshAccrssTokenTask refreshAccrssTokenTask;
    
	public void setRefreshAccrssTokenTask(RefreshAccrssTokenTask refreshAccrssTokenTask) {
		this.refreshAccrssTokenTask = refreshAccrssTokenTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		refreshAccrssTokenTask.refreshToken();
		
	}

}
