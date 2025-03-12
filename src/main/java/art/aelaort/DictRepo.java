package art.aelaort;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

import static art.aelaort.tables.StatusesTasks.STATUSES_TASKS;
import static art.aelaort.tables.TypesTasks.TYPES_TASKS;

@Component
@RequiredArgsConstructor
public class DictRepo {
	private final DefaultDSLContext dsl;

	@Cacheable("statuses")
	public Map<String, Integer> getStatuses() {
		return dsl.select(STATUSES_TASKS.NAME, STATUSES_TASKS.ID)
				.from(STATUSES_TASKS)
				.fetchMap(STATUSES_TASKS.NAME, STATUSES_TASKS.ID);
	}

	@Cacheable("types")
	public Map<String, Integer> getTypes() {
		return dsl.select(TYPES_TASKS.NAME, TYPES_TASKS.ID)
				.from(TYPES_TASKS)
				.fetchMap(TYPES_TASKS.NAME, TYPES_TASKS.ID);
	}
}
