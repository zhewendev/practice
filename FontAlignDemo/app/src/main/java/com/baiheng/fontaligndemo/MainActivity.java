package com.baiheng.fontaligndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String text = "Hooray! It's snowing! It's time to make a snowman.James runs out. He makes a big pile of snow. He puts a big snowball on top. He adds a scarf and a hat. He adds an orange for the nose. He adds coal for the eyes and buttons.In the evening, James opens the door. What does he see? The snowman is moving! James invites him in. The snowman has never been inside a house. He says hello to the cat. He plays with paper towels.A moment later, the snowman takes James's hand and goes out.They go up, up, up into the air! They are flying! What a wonderful night!The next morning, James jumps out of bed. He runs to the door.He wants to thank the snowman. But he's gone.";
    private String text1 = "垃圾桶讲完了，哦不，是缓存层级讲完了。这里提一句，其实还有一层没有提到，因为它不在Recycler这个类中，它在ChildHelper类中，其中有个mHiddenViews,是个缓存被隐藏的ViewHolder的ArrayList。到这里我想大家对这几层缓存心里已经有个数了，但是还远远不够，这么多层缓存是怎么工作的？什么时候用什么缓存？各个缓存之间有没有什么PY交易？如果让你自己写一个LayoutManager你能处理好缓存问题么？就好比垃圾分类后，我们知道每种垃圾桶的定义和功能，但是面对大妈灵魂拷问我依然分不清自己是什么垃圾，我太难了～相比之下，RV的几个垃圾桶简单多了，下面我们一起来看看，这些个缓存都咋用。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SelfAdaptionTextView textView = (SelfAdaptionTextView) findViewById(R.id.tv_adaptive);
    }
}
