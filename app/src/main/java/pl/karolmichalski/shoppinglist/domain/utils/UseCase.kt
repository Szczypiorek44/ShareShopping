package pl.karolmichalski.shoppinglist.domain.utils

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

abstract class UseCase<in Params, Result>(
		private val executor: Executor,
		private val observeScheduler: Scheduler
) {
	private var disposables: CompositeDisposable = CompositeDisposable()

	/**
	 * Builds an [Observable] which will be used when executing the current [UseCase].
	 */
	protected abstract fun buildUseCaseObservable(params: Params): Single<Result>

	/**
	 * Executes the current use case.
	 *
	 * @param observer [DisposableObserver] which will be listening to the observable build
	 * by [.buildUseCaseObservable] ()} method.
	 * @param params   Parameters (Optional) used to build/execute this use case.
	 */
	fun execute(
			params: Params,
			observer: DisposableSingleObserver<Result>
	) {
		val observable = this.buildUseCaseObservable(params)
				.subscribeOn(Schedulers.from(executor))
				.observeOn(observeScheduler)
		addDisposable(observable.subscribeWith(observer))
	}

	/**
	 * Dispose from current [CompositeDisposable].
	 */
	fun dispose() {
		if (!disposables.isDisposed) {
			disposables.dispose()
			disposables = CompositeDisposable()
		}
	}

	/**
	 * Dispose from current [CompositeDisposable].
	 */
	private fun addDisposable(disposable: Disposable) {
		disposables.add(disposable)
	}
}
