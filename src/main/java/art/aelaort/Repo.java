package art.aelaort;

import art.aelaort.tables.Tasks;
import lombok.RequiredArgsConstructor;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Repo {
	private final DefaultDSLContext dsl;
	private final DictRepo dictRepo;
	private final Tasks t = Tasks.TASKS;

	public void getNewTasks(String type) {
		Integer typeid = dictRepo.getTypes().get(type);
		Integer status = dictRepo.getStatuses().get("todo");
		dsl.select()
				.from(t)
				.where(t.TYPE_ID.eq(typeid))
				.and(t.STATUS_ID.eq(status))
				.fetch();
	}
}
