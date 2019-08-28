### 欢迎来到这里，证明我们有缘， 如果你喜欢的话就请给star吧，你的star会是我持续更新的动力，与大家共勉！加油！

#### 展示效果：

![效果图1](pic/device-2019-06-15-154310_2.gif)



使用方式：
gradle用法：

```
  allprojects {
    repositories {
	      maven { url 'https://jitpack.io' }
	   }
	}
```

```
  dependencies {
	     implementation 'com.github.Johnjson:GuideActivity:v1.0.1'
	}
```

maven用法：

```
   <repositories>
	<repository>
           <id>jitpack.io</id>
	   <url>https://jitpack.io</url>
	</repository>
   </repositories>
```

```
     <dependency>
	 <groupId>com.github.Johnjson</groupId>
	 <artifactId>GuideActivity</artifactId>
	 <version>1.0.0</version>
     </dependency>

```

项目中使用：

```
    <com.click.guide.guide_lib.GuideCustomViews
        android:id="@+id/guide_CustomView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

```
public class MainActivity extends AppCompatActivity implements CallBack {

    private GuideCustomViews GuideCustomViews;
    private final int[] mPageImages = {
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3,
            R.mipmap.guide_4
    };

    private final int[] mGuidePoint = {
            R.mipmap.icon_guide_point_select,
            R.mipmap.icon_guide_point_unselect
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        GuideCustomViews = findViewById(R.id.guide_CustomView);
        GuideCustomViews.setData(mPageImages, mGuidePoint, this);
    }

    @Override
    public void callSlidingPosition(int position) {
        Log.e("callSlidingPosition", "滑动位置 callSlidingPosition " + position);
    }

    @Override
    public void callSlidingLast() {
        Log.e("callSlidingLast", "滑动到最后一个callSlidingLast");
    }
    
    @Override
    public void onClickLastListener() {
        Log.e("callSlidingLast", "点击最后一个view");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GuideCustomViews.clear();
    }
}
```

欢迎star,[项目地址](https://github.com/Johnjson/GuideActivity)
