package art.aelaort;

import art.aelaort.il_processing.tables.TasksDownloadTorrent;
import art.aelaort.il_processing.tables.TasksGenerateHls;
import art.aelaort.models.repo.tasks.DownloadTask;
import art.aelaort.models.repo.tasks.GenerateTask;
import lombok.RequiredArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.jooq.Records.mapping;

@Component
@RequiredArgsConstructor
public class Repo {
	private final DefaultDSLContext dsl;
	private final DictRepo dictRepo;
	private final TasksDownloadTorrent tdt = TasksDownloadTorrent.TASKS_DOWNLOAD_TORRENT;
	private final TasksGenerateHls tgh = TasksGenerateHls.TASKS_GENERATE_HLS;

	public Optional<GenerateTask> getNewGenerateTask() {
		Integer status = dictRepo.getTaskStatuses().get("todo");
		return dsl.select(tgh.SRC_LOCAL_DIR, tgh.TARGET_LOCAL_DIR)
				.from(tgh)
				.where(tgh.STATUS_ID.eq(status))
				.orderBy(tgh.CREATED_AT.asc())
				.fetchOptional(mapping(GenerateTask::new));
	}

	public Optional<DownloadTask> getNewDownloadTask() {
		Integer status = dictRepo.getTaskStatuses().get("todo");
		return dsl.select(tdt.TORRENT_FILE_PATH)
				.from(tdt)
				.where(tdt.STATUS_ID.eq(status))
				.orderBy(tgh.CREATED_AT.asc())
				.fetchOptional(mapping(DownloadTask::new));
	}
}
