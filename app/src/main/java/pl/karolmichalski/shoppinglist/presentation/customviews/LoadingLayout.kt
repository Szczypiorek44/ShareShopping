package pl.karolmichalski.shoppinglist.presentation.customviews

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar

class LoadingLayout @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private companion object {
        private const val ANIM_DURATION = 500
        private const val BACKGROUND_COLOR = "#33000000"
        private const val PROGRESS_BAR_SIZE = 60
    }

    init {
        val progressSize = (context.resources.displayMetrics.density * PROGRESS_BAR_SIZE).toInt()

//        alpha = 0f
        isClickable = true
        isFocusable = true
        visibility = View.GONE
//        id = R.id.loadingLayout

        setBackgroundColor(Color.parseColor(BACKGROUND_COLOR))

        val progressBar = ProgressBar(context)
        progressBar.layoutParams = LayoutParams(progressSize, progressSize, Gravity.CENTER)
        addView(progressBar)
    }
//
//    fun show() {
//        visibility = View.VISIBLE
//        animate().alpha(1f).duration = ANIM_DURATION.toLong()
//    }
//
//    fun hide() {
//        animate().alpha(0f).setDuration(ANIM_DURATION.toLong()).withEndAction { visibility = View.GONE }
//    }
}