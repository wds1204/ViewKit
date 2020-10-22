package com.wds.viewkit.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.wds.viewkit.R;

public class SlideView extends HorizontalScrollView {
    private final GestureDetector gestureDetector;
    private final Context mContext;
    private final int mMenuWidth;
    private View mMenuView;
    ;//菜单View,
    private View mContentView;//内容View
    private ImageView mShadowIv;
    private boolean mMenuIsOpen;
    private boolean isIntercept;

    public SlideView(Context context) {
        this(context, null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取自定义的右边留出的宽度
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideView);
        float rightPadding = array.getDimension(
                R.styleable.SlideView_rightPadding, dip2px(50));

        // 4.1.2 计算菜单的宽度 = 屏幕的宽度 - 自定义右边留出的宽度
        mMenuWidth = (int) (getScreenWidth() - rightPadding);
        array.recycle();

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("TAG", "velocityX===" + velocityX);
                /*快速滑动*/
                if (mMenuIsOpen) {
                    if (velocityX < -500) {
                        toggleMenu();
                        return true;
                    }
                } else {
                    if (velocityX > 500) {
                        toggleMenu();
                        return true;
                    }
                }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        mContext = context;
    }

    /**
     * 切换菜单的状态
     */
    private void toggleMenu() {
        if (mMenuIsOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }


    /**
     * 把dip 转成像素
     */
    private float dip2px(int dip) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
    /*实例化布局结束*/
    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        /*指定菜单和内容View的宽高*/
        //获取根View的外层容器 LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);

        int containerChildCount = container.getChildCount();
        if (containerChildCount > 2) {
            throw new IllegalStateException("SlidingMenu 根布局LinearLayout下面只允许两个布局,菜单布局和主页内容布局");
        }
        /*容器中的菜单布局和内容布局*/
        mMenuView = container.getChildAt(0);

        mContentView = container.getChildAt(1);

        // 3.指定内容和菜单布局的宽度
        // 3.1 菜单的宽度 = 屏幕的宽度 - 自定义的右边留出的宽度
        mMenuView.getLayoutParams().width = mMenuWidth;
        // 3.2 内容的宽度 = 屏幕的宽度
        mContentView.getLayoutParams().width = getScreenWidth();

        mMenuView.getLayoutParams().width = mMenuWidth;

        mContentView.getLayoutParams().width = getScreenWidth();

    }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 布局指定后会从新摆放子布局，当其摆放完毕后，让菜单滚动到不可见状态
        if (changed) {
            scrollTo(mMenuWidth, 0);
        }
    }


    /**
     * 打开菜单
     */
    private void openMenu() {
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }


    /**
     * 获取屏幕的宽度
     */
    public int getScreenWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        isIntercept=false;
        if (mMenuIsOpen) {
            float currentX = ev.getX();

            if (currentX > mMenuWidth) {
                closeMenu();
                isIntercept=true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override public boolean onTouchEvent(MotionEvent ev) {
        if(isIntercept) {
            return true;
        }

        if (gestureDetector.onTouchEvent(ev)) {
            return true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                Log.e("TAG", "scrollX====" + scrollX);
                if (scrollX > mMenuWidth / 2) {
                    closeMenu();
                } else {
                    openMenu();
                }
                return false;
//                break;
        }

        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);


        // l 是 当前滚动的x距离  在滚动的时候会不断反复的回调这个方法
        Log.e("TAG", "onScrollChanged=========" + l + "");
        // 6. 实现菜单左边抽屉样式的动画效果
//        mMenuView.setTranslationX(l*0.8f);

        // 7.给内容添加阴影效果 - 计算梯度值
//        float gradientValue = l * 1f / mMenuWidth;// 这是  1 - 0 变化的值

//         7.给内容添加阴影效果 - 给阴影的View指定透明度   0 - 1 变化的值
//        float shadowAlpha = 1 - gradientValue;
//        mShadowIv.setAlpha(shadowAlpha);

    }
}
