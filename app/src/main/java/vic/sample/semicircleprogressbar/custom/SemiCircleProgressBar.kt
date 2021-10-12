package vic.sample.semicircleprogressbar.custom

import android.content.Context
import android.graphics.*
import android.icu.text.Transliterator
import android.util.AttributeSet
import android.view.View
import vic.sample.semicircleprogressbar.R


class SemiCircleProgressBar : View {

    companion object {
        const val UNINITIALIZED_INT_VALUE = -1
        const val MAXIMUM_PROGRESS_DEFAULT_VALUE = 100
        const val MINIMUM_PROGRESS_DEFAULT_VALUE = 0
        const val CURRENT_PROGRESS_DEFAULT_VALUE = 50
        const val PADDING_DEFAULT_VALUE = 4
        const val WIDTH_DEFAULT_VALUE = 16
        const val HOLDER_WIDTH_DEFAULT_VALUE = 24
        const val CLOCKWISE_DEFAULT = true

        const val POSITION_TOP = 0
        const val POSITION_BOTTOM = 1
        const val POSITION_LEFT = 2
        const val POSITION_RIGHT = 3
    }

    private var mPadding = PADDING_DEFAULT_VALUE.dp

    private var mMaxProgress = MAXIMUM_PROGRESS_DEFAULT_VALUE
    private var mMinProgress = MINIMUM_PROGRESS_DEFAULT_VALUE
    private var mCurrentProgress = CURRENT_PROGRESS_DEFAULT_VALUE

    private var mProgressBarColor = UNINITIALIZED_INT_VALUE
    private var mProgressBarHolderColor = UNINITIALIZED_INT_VALUE

    private var mProgressBarWidth = UNINITIALIZED_INT_VALUE
    private var mProgressBarHolderWidth = UNINITIALIZED_INT_VALUE

    private var mProgressClockwise = CLOCKWISE_DEFAULT

    private var mPosition = POSITION_TOP

    private var mTop = 0
    private var mLeft = 0
    private var mRight = 0
    private var mBottom = 0

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context = context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context = context, attrs = attrs)
    }

    /*
    * Init Attr
    * */
    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.SemiCircleProgressBar, 0, 0);

        /*
        * Init progress bar width and color
        * */
        mProgressBarColor =
            typedArray.getColor(R.styleable.SemiCircleProgressBar_progressColor, Color.BLUE)
        mProgressBarHolderColor =
            typedArray.getColor(R.styleable.SemiCircleProgressBar_progressHolderColor, Color.GRAY)

        mProgressBarWidth =
            typedArray.getInteger(
                R.styleable.SemiCircleProgressBar_progressWidth, WIDTH_DEFAULT_VALUE
            )
        mProgressBarHolderWidth =
            typedArray.getInteger(
                R.styleable.SemiCircleProgressBar_progressHolderWidth, HOLDER_WIDTH_DEFAULT_VALUE
            )
        /*
        * The clockwise progress
        * */
        mProgressClockwise =
            typedArray.getBoolean(
                R.styleable.SemiCircleProgressBar_progressClockwise, mProgressClockwise
            )

        /*
        * The circle start angle position
        * */
        mPosition =
            typedArray.getInteger(R.styleable.SemiCircleProgressBar_position, mPosition)

        /*
        * Init the maximum and minimum
        * */
        mMaxProgress =
            typedArray.getInteger(
                R.styleable.SemiCircleProgressBar_maxProgress, mMaxProgress
            )
        mMinProgress =
            typedArray.getInteger(
                R.styleable.SemiCircleProgressBar_minProgress, mMinProgress
            )

        mCurrentProgress =
            typedArray.getInteger(
                R.styleable.SemiCircleProgressBar_currentProgress, mCurrentProgress
            )

        /*
        * Recycle the typedArray
        * */
        typedArray.recycle()
    }

    /*
    * Measure the Width & Height
    * */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mPadding =
            if (mProgressBarWidth > mProgressBarHolderWidth)
                (mProgressBarWidth + 2).dp
            else
                (mProgressBarHolderWidth + 2).dp

        mTop = mPadding
        mLeft = mPadding
        mRight = measuredWidth
        mBottom = (measuredHeight * 2)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (mMaxProgress > mMinProgress && mCurrentProgress in mMinProgress..mMaxProgress) {

            var startAngle = when (mPosition) {
                POSITION_BOTTOM -> 0
                POSITION_LEFT -> 90
                POSITION_RIGHT -> 270
                else -> 180
            }

            var progressAmount =
                (mCurrentProgress.toFloat() / (mMaxProgress - mMinProgress).toFloat() * 180)

            canvas.drawArc(
                getProgressBarRectF(), startAngle.toFloat(), 180.toFloat(), false,
                getPaint(mProgressBarHolderColor, mProgressBarHolderWidth)
            )

            if (!mProgressClockwise) {
                startAngle -= 180
                progressAmount = -progressAmount
            }

            canvas.drawArc(
                getProgressBarRectF(), startAngle.toFloat(), progressAmount, false,
                getPaint(mProgressBarColor, mProgressBarWidth)
            )
        }
    }

    private fun getPaint(color: Int, width: Int): Paint {
        val paint = Paint()

        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = width.toFloat()
        paint.isAntiAlias = true
        paint.strokeCap = Paint.Cap.ROUND

        return paint
    }

    private fun getProgressBarRectF(): RectF {
        return when (mPosition) {
            POSITION_RIGHT -> {
                RectF(
                    -mRight.toFloat() + mPadding * 3,
                    mTop.toFloat(),
                    (mRight - mPadding).toFloat(),
                    (mBottom / 2 - mPadding).toFloat()
                )
            }
            POSITION_LEFT -> {
                RectF(
                    mLeft.toFloat(),
                    mTop.toFloat(),
                    (mRight * 2 - mPadding * 3).toFloat(),
                    (mBottom / 2 - mPadding).toFloat()
                )
            }
            POSITION_BOTTOM -> {
                RectF(
                    mLeft.toFloat(),
                    -mBottom.toFloat() / 2 + mPadding * 3,
                    (mRight - mPadding).toFloat(),
                    (mBottom / 2 - mPadding).toFloat()
                )
            }
            else -> {
                RectF(
                    mLeft.toFloat(),
                    mTop.toFloat(),
                    (mRight - mPadding).toFloat(),
                    (mBottom - mPadding * 3).toFloat()
                )
            }
        }


    }

    fun setProgress(percent: Int) {
        this.mCurrentProgress = percent
        invalidate()
    }

    fun setProgressColor(color: Int) {
        this.mProgressBarColor = color
        invalidate()
    }

    fun setProgressHolderColor(color: Int) {
        this.mProgressBarHolderColor = color
        invalidate()
    }

    fun setProgressWidth(width: Int) {
        this.mProgressBarWidth = width
    }

    fun setProgressHolderWidth(width: Int) {
        this.mProgressBarHolderWidth = width
    }
}
