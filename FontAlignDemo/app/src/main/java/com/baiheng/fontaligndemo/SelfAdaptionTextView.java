package com.baiheng.fontaligndemo;

import android.content.Context;
import android.graphics.Canvas;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 自适应对齐的TextView（中英文排版对齐）
 * 非中文单词换行时自适应截断
 */
public class SelfAdaptionTextView extends TextView {
    private static final String TAG = SelfAdaptionTextView.class.getSimpleName();

    private static final String BLANK = " ";
    private float mLineY;   //绘制文字的起始Y坐标
    private int mFontViewWidth; //文字绘制区域宽度
    private int paragraphSpacing = dipToPx(getContext(), 15);    //段落间距
    private int mLineCount = 0; //当前所有行数
    private ArrayList<List<List<String>>> mParagraphLineList; //当前所有行数的集合
    private ArrayList<List<String>> mParagraphWordList; //每一段单词的内容集合
    private String[] vowel = {"a", "e", "i", "o", "u"}; //英语单词元音字母
    private List<String> vowels = Arrays.asList(vowel);
    private TextPaint mTextPaint = getPaint();

    public SelfAdaptionTextView(Context context) {
        this(context, null);
    }

    public SelfAdaptionTextView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public SelfAdaptionTextView(Context context, AttributeSet attr, int defStyleAttr) {
        this(context, attr, defStyleAttr, 0);
    }

    public SelfAdaptionTextView(Context context, AttributeSet attr, int defStyleAttr, int defStyleRes) {
        super(context, attr, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLineY = 0;
        mParagraphLineList = null;
        mParagraphWordList = null;
        mFontViewWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
        getParagraphWordList();
        for (List<String> fontList : mParagraphWordList) {
            mParagraphLineList.add(getLineList(fontList));
        }
        Log.i(TAG, "mParagraphLineList.size = " + mParagraphLineList.size() + ",mLineCount = " + mLineCount);
        setMeasuredDimension(mFontViewWidth, (mParagraphLineList.size() - 1) * paragraphSpacing + mLineCount * getLineHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mTextPaint.setColor(getCurrentTextColor());
        mTextPaint.drawableState = getDrawableState();
        mLineY = 0;
        mLineY += getTextSize() + getPaddingTop();
        adaptiveDraw(canvas);
    }

    /**
     * 获取文字段落集合
     */
    private List<List<String>> getParagraphWordList() {
        String text = getText().toString().replaceAll(" {2}", "").replaceAll(" {3}", "").replaceAll("\\r", "").trim();
        String[] items = text.split("\\n");
        mLineCount = 0;
        mParagraphLineList = new ArrayList<>();
        mParagraphWordList = new ArrayList<>();
        for (String item : items) {
            if (item.length() != 0) {
                mParagraphWordList.add(interceptWordList(item));
            }
        }
        return mParagraphWordList;

    }

    /**
     * 计算每一段文字每一行需要显示的单词集合，显示不下进行截断换行
     */
    private synchronized List<List<String>> getLineList(List<String> fontList) {
        Log.i(TAG, "getLineList");
        StringBuilder stringBuilder = new StringBuilder();  //当前行显示的内容
        List<List<String>> lineLists = new ArrayList<>();   // 一段文字各行需要显示的单词总集合
        List<String> lineList = new ArrayList<>();          //当前行需要显示的单词集合

        String temp = "";
        String font;
        int fontListSize = fontList.size();     //需要显示的文本总单词长度
        for (int i = 0; i < fontListSize; i++) {
            font = fontList.get(i);

            if (!TextUtils.isEmpty(temp)) {
                stringBuilder.append(temp);
                lineList.add(temp);
                if (!isContainCN(temp)) {
                    stringBuilder.append(BLANK);
                }
                temp = "";
            }

            if ((i + 1) < fontListSize) {
                String nextFont = fontList.get(i + 1);
                if (isContainCN(nextFont)) {
                    stringBuilder.append(font);
                } else {
                    stringBuilder.append(font).append(BLANK);
                }
            } else {
                stringBuilder.append(font);
            }

            lineList.add(font);
            float width = StaticLayout.getDesiredWidth(stringBuilder.toString(), getPaint());
            //判断当前行内容长度是否超出显示宽度，若超出，进行截断处理
            if (width > mFontViewWidth) {

                int lastIndex = lineList.size() - 1;
                String lastWord = lineList.get(lastIndex);
                String lastTemp = "";
                lineList.remove(lastIndex); //删除该行最后一个单词
                if (isContainCN(lastWord)) {
                    addLines(lineLists, lineList);
                    lastTemp = lastWord;
                } else {
                    lastTemp = interceptCharacter(stringBuilder, lastWord, lineLists, lineList);
                }

                stringBuilder.delete(0, stringBuilder.toString().length());
                temp = lastTemp;
            }

            if (lineList.size() > 0 && i == fontListSize - 1) {
                addLines(lineLists, lineList);
            }
        }

        if (!TextUtils.isEmpty(temp)) {
            lineList.add(temp);
            addLines(lineLists, lineList);
        }

        mLineCount += lineLists.size();
        return lineLists;
    }

    /**
     * 截取字符，返回单词被截断的后半部分
     *
     * @param stringBuilder 未截断前当前行的显示文本
     * @param lastWord      最后一个单词
     * @param lineLists     各行单词集合的总集合
     * @param lineList      当前行的单词集合
     * @return 返回最后一个单词被截断的后半部分
     */
    private String interceptCharacter(StringBuilder stringBuilder, String lastWord, List<List<String>> lineLists, List<String> lineList) {
        Log.i(TAG, "interceptCharacter");
        int lastWordLength = lastWord.length();
        String subString = stringBuilder.substring(0, stringBuilder.length() - lastWordLength - 1);
        stringBuilder.delete(0, stringBuilder.toString().length());
        stringBuilder.append(subString).append(BLANK);
        String tempLastWord = "";

        float width = 0;
        int cutOffIndex = 0;    //字符截断处下标

        if (lastWordLength <= 3) {
            addLines(lineLists, lineList);
            tempLastWord = lastWord;
            return tempLastWord;
        }
        //遍历最后一个单词，判断字符截断处
        for (int j = 0; j < lastWordLength; j++) {
            tempLastWord = String.valueOf(lastWord.charAt(j));
            stringBuilder.append(tempLastWord);
            // 根据元音字母进行截断
            if (vowels.contains(tempLastWord) && (j + 1 < lastWordLength)) {
                String nextTempLastWord = String.valueOf(lastWord.charAt(j + 1));
                stringBuilder.append(nextTempLastWord);
                width = StaticLayout.getDesiredWidth(stringBuilder.toString(), getPaint());
                cutOffIndex = j;
                if (width > mFontViewWidth) {
                    // 单词截断后，截断字符小于2个时，不进行截断操作
                    if (j >= 2) {
                        String lastFinalWord = lastWord.substring(0, cutOffIndex) + "-";
                        lineList.add(lastFinalWord);
                        addLines(lineLists, lineList);
                        tempLastWord = lastWord.substring(cutOffIndex, lastWordLength);
                    } else {
                        addLines(lineLists, lineList);
                        tempLastWord = lastWord;
                    }
                    break;
                } else {
                    addLines(lineLists, lineList);
                    tempLastWord = lastWord;
                    break;
                }
            }

            width = StaticLayout.getDesiredWidth(stringBuilder.toString(), getPaint());
            if (width > mFontViewWidth) {
                if (j > 2) {
                    String lastFinalWord = lastWord.substring(0, j - 1) + "-";
                    lineList.add(lastFinalWord);
                    addLines(lineLists, lineList);
                    tempLastWord = lastWord.substring(j - 1, lastWordLength);
                } else {
                    addLines(lineLists, lineList);
                    tempLastWord = lastWord;
                }
                break;
            }
        }
        return tempLastWord;

    }

    /**
     * 截取并获得一段文字的单词列表
     *
     * @param text
     * @return
     */
    private synchronized List<String> interceptWordList(String text) {
        if (TextUtils.isEmpty(text)) {
            return new ArrayList<>();
        }
        Log.i(TAG, "interceptWordList ");
        List<String> fontList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();  //用于拼接字符为单词
        for (int i = 0; i < text.length(); i++) {
            String charAt = String.valueOf(text.charAt(i));
            if (BLANK.equals(charAt)) {
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    fontList.add(stringBuilder.toString().replaceAll(BLANK, ""));
                    stringBuilder.delete(0, stringBuilder.length());
                }
                continue;
            }

            if (checkIsSymbol(charAt)) {
                boolean isEmptyString = TextUtils.isEmpty(stringBuilder);
                stringBuilder.append(charAt);
                if (!isEmptyString) {
                    fontList.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                }
            } else {
                if (isContainCN(stringBuilder.toString())) {
                    fontList.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                }
                stringBuilder.append(charAt);
            }
        }

        if (stringBuilder.length() != 0) {
            fontList.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }

        return fontList;

    }

    private synchronized void adaptiveDraw(Canvas canvas) {
        Log.i(TAG, "adaptiveDraw");
        int size = mParagraphWordList.size();
        for (int j = 0; j < size; j++) {
            List<List<String>> lineList = mParagraphLineList.get(j);
            for (int i = 0; i < lineList.size(); i++) {
                List<String> lineWords = lineList.get(i);
                Log.i(TAG, Arrays.toString(lineWords.toArray()));
                if (i == lineList.size() - 1) {
                    drawScaledEndText(canvas, lineWords);
                } else {
                    drawScaledText(canvas, lineWords);
                }
                mLineY += getLineHeight();
            }
            mLineY += paragraphSpacing;
        }
    }

    /**
     * 绘制最后一行文字
     *
     * @param canvas
     * @param lineWords
     */
    private void drawScaledEndText(Canvas canvas, List<String> lineWords) {
        if (canvas == null || lineWords == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : lineWords) {
            if (isContainCN(word)) {
                stringBuilder.append(word);
            } else {
                stringBuilder.append(word).append(BLANK);
            }
        }

        /**
         * 最后一行适配布局方向
         * android:gravity=""
         * android:textAlignment=""
         * 默认不设置则为左边
         * 如果同时设置gravity和textAlignment属性，则以textAlignment的属性为准
         * */
        final int layoutDirection = getLayoutDirection();
        final int absoluteGravity = Gravity.getAbsoluteGravity(getGravity(), layoutDirection);
        int lastGravity = absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
        int textAlignment = getTextAlignment();
        if (TEXT_ALIGNMENT_TEXT_START == textAlignment
                || TEXT_ALIGNMENT_VIEW_START == textAlignment
                || Gravity.LEFT == lastGravity) {
            canvas.drawText(stringBuilder.toString(), 0, mLineY, mTextPaint);
        } else if (TEXT_ALIGNMENT_TEXT_END == textAlignment
                || TEXT_ALIGNMENT_VIEW_END == textAlignment
                || Gravity.RIGHT == lastGravity) {
            float width = StaticLayout.getDesiredWidth(stringBuilder.toString(), getPaint());
            canvas.drawText(stringBuilder.toString(), mFontViewWidth - width, mLineY, mTextPaint);
        } else {
            float width = StaticLayout.getDesiredWidth(stringBuilder.toString(), getPaint());
            canvas.drawText(stringBuilder.toString(), (mFontViewWidth - width) / 2, mLineY, mTextPaint);
        }

    }

    /**
     * 绘制左右对齐的text
     *
     * @param canvas
     * @param lineWords
     */
    private void drawScaledText(Canvas canvas, List<String> lineWords) {
        if (canvas == null || lineWords == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : lineWords) {
            stringBuilder.append(word);
        }
        Log.i(TAG, "drawScaledText" + stringBuilder.toString());
        float lineWidth = StaticLayout.getDesiredWidth(stringBuilder, getPaint());
        float currentWordX = 0f;
        float d = (mFontViewWidth - lineWidth) / (lineWords.size() - 1);
        canvas.drawText(lineWords.get(0), 0, mLineY, mTextPaint);
        for (String word : lineWords) {
            canvas.drawText(word, currentWordX, mLineY, mTextPaint);
            currentWordX += StaticLayout.getDesiredWidth(word + "", mTextPaint) + d;
        }
    }


    /**
     * 将各行显示的单词内容添加到总集合中
     *
     * @param lineLists
     * @param lineList
     */
    private void addLines(List<List<String>> lineLists, List<String> lineList) {
        if (lineLists == null || lineList == null) {
            return;
        }
        List<String> tempLineList = new ArrayList<>(lineList);
        lineLists.add(tempLineList);
        lineList.clear();
    }

    public static int dipToPx(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int) (var1 * var2 + 0.5F);
    }

    /**
     * 判断是否包含标点符号等内容
     *
     * @param string
     * @return
     */
    public boolean checkIsSymbol(String string) {

        String tempString = string;
        tempString = tempString.replaceAll("\\p{P}", "");
        return string.length() != tempString.length();
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param string
     * @return
     */
    public boolean isContainCN(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        return bytes.length != string.length();
    }
}
