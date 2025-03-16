package art.aelaort;

import art.aelaort.models.repo.tasks.DownloadTask;
import art.aelaort.models.repo.tasks.GenerateTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static javax.management.timer.Timer.ONE_MINUTE;

@Slf4j
@Component
@RequiredArgsConstructor
public class Cron {
	private final Repo repo;

	@Scheduled(fixedDelay = ONE_MINUTE * 5)
	public void downloadTasks() {
		Optional<DownloadTask> downloadTaskOp = repo.getNewDownloadTask();
		if (downloadTaskOp.isEmpty()) {
			return;
		}
		DownloadTask downloadTask = downloadTaskOp.get();
	}

	@Scheduled(fixedDelay = ONE_MINUTE * 5)
	public void generateTasks() {
		Optional<GenerateTask> generateTaskOp = repo.getNewGenerateTask();
		if (generateTaskOp.isEmpty()) {
			 return;
		}
		GenerateTask generateTask = generateTaskOp.get();
	}
}
