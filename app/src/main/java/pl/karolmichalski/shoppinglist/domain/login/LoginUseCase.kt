package pl.karolmichalski.shoppinglist.domain.login

import io.reactivex.Scheduler
import io.reactivex.Single
import pl.karolmichalski.shoppinglist.domain.FirebaseRepository
import pl.karolmichalski.shoppinglist.domain.utils.UseCase
import java.util.concurrent.Executor

class LoginUseCase constructor(
		executor: Executor,
		scheduler: Scheduler,
		private val repository: FirebaseRepository
) : UseCase<Unit, Unit>(executor, scheduler) {

	override fun buildUseCaseObservable(params: Unit): Single<Unit> {
		return repository.getLoginSingle()
	}
}