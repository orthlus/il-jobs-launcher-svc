package art.aelaort;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static art.aelaort.il_processing.tables.StatusesTasks.STATUSES_TASKS;
import static art.aelaort.il_infra.tables.StorageTypes.STORAGE_TYPES;

@Component
@RequiredArgsConstructor
public class DictRepo {
	private final DefaultDSLContext dsl;

	@Cacheable("storage-types")
	public Map<String, Integer> getStorageTypes() {
		return dsl.select(STORAGE_TYPES.NAME, STORAGE_TYPES.ID)
				.from(STORAGE_TYPES)
				.fetchMap(STORAGE_TYPES.NAME, STORAGE_TYPES.ID);
	}

	@Cacheable("task-statuses")
	public Map<String, Integer> getTaskStatuses() {
		return dsl.select(STATUSES_TASKS.NAME, STATUSES_TASKS.ID)
				.from(STATUSES_TASKS)
				.fetchMap(STATUSES_TASKS.NAME, STATUSES_TASKS.ID);
	}
}
