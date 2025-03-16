package art.aelaort;

import art.aelaort.il_processing.tables.TasksDownloadTorrent;
import art.aelaort.il_processing.tables.TasksGenerateHls;
import lombok.RequiredArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Repo {
	private final DefaultDSLContext dsl;
	private final DictRepo dictRepo;
	private final TasksDownloadTorrent tdt = TasksDownloadTorrent.TASKS_DOWNLOAD_TORRENT;
	private final TasksGenerateHls tgh = TasksGenerateHls.TASKS_GENERATE_HLS;

	public void getNewGenerateTasks() {
		Integer status = dictRepo.getTaskStatuses().get("todo");
		dsl.select()
				.from(tgh)
				.where(tgh.STATUS_ID.eq(status))
				.fetch();
	}

	public void getNewDownloadTasks() {
		Integer status = dictRepo.getTaskStatuses().get("todo");
		dsl.select()
				.from(tdt)
				.where(tdt.STATUS_ID.eq(status))
				.fetch();
	}
}
