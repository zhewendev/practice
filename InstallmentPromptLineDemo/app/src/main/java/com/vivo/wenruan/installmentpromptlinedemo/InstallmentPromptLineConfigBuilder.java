package com.vivo.wenruan.installmentpromptlinedemo;

import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

import java.text.NumberFormat;

public class InstallmentPromptLineConfigBuilder {
    float min;
    float max;
    float progress;
    boolean floatType;
    int trackSize;
    int secondTrackSize;
    int thumbRadius;
    int thumbRadiusOnDragging;
    int trackColor;
    int secondTrackColor;
    int thumbColor;
    int sectionCount;
    boolean showSectionMark;
    boolean autoAdjustSectionMark;
    boolean showSectionText;
    int sectionTextSize;
    int sectionTextColor;
    @InstallmentPromptLine.TextPosition
    int sectionTextPosition;
    int sectionTextInterval;
    boolean showThumbText;
    int thumbTextSize;
    int thumbTextColor;
    boolean showProgressInFloat;
    long animDuration;
    boolean touchToSeek;
    boolean seekBySection;
    int signColor;
    int signTextSize;
    int signTextColor;
    boolean showSign;
    String[] bottomSidesLabels;
    float thumbBgAlpha; //  alpha of thumb shadow
    float thumbRatio; // ratio of thumb shadow
    boolean showThumbShadow;
    InstallmentPromptLine mSignSeekBar;
    String unit;
    int signArrowHeight;
    int signArrowWidth;
    int signRound;
    int signHeight; //sign Height
    int signWidth; //sign width
    int signBorderSize; // border size
    boolean showSignBorder; // show sign border
    boolean signArrowAutofloat;
    int signBorderColor;// color of border color
    NumberFormat format;
    boolean reverse;

    InstallmentPromptLineConfigBuilder(InstallmentPromptLine signSeekBar) {
        mSignSeekBar = signSeekBar;
    }

    public void build() {
        mSignSeekBar.config(this);
    }

    public InstallmentPromptLineConfigBuilder min(float min) {
        this.min = min;
        this.progress = min;
        return this;
    }

    public InstallmentPromptLineConfigBuilder max(float max) {
        this.max = max;
        return this;
    }

    public InstallmentPromptLineConfigBuilder progress(float progress) {
        this.progress = progress;
        return this;
    }

    public InstallmentPromptLineConfigBuilder floatType() {
        this.floatType = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder trackSize(int dp) {
        this.trackSize = InstallmentPromptLineUtils.dp2px(dp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder secondTrackSize(int dp) {
        this.secondTrackSize = InstallmentPromptLineUtils.dp2px(dp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbRadius(int dp) {
        this.thumbRadius = InstallmentPromptLineUtils.dp2px(dp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbRadiusOnDragging(int dp) {
        this.thumbRadiusOnDragging = InstallmentPromptLineUtils.dp2px(dp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder trackColor(@ColorInt int color) {
        this.trackColor = color;
        this.sectionTextColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder secondTrackColor(@ColorInt int color) {
        this.secondTrackColor = color;
        this.thumbColor = color;
        this.thumbTextColor = color;
        this.signColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbColor(@ColorInt int color) {
        this.thumbColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder sectionCount(@IntRange(from = 1) int count) {
        this.sectionCount = count;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showSectionMark() {
        this.showSectionMark = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder autoAdjustSectionMark() {
        this.autoAdjustSectionMark = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showSectionText() {
        this.showSectionText = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder sectionTextSize(int sp) {
        this.sectionTextSize = InstallmentPromptLineUtils.sp2px(sp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder sectionTextColor(@ColorInt int color) {
        this.sectionTextColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder sectionTextPosition(@SignSeekBar.TextPosition int position) {
        this.sectionTextPosition = position;
        return this;
    }

    public InstallmentPromptLineConfigBuilder sectionTextInterval(@IntRange(from = 1) int interval) {
        this.sectionTextInterval = interval;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showThumbText() {
        this.showThumbText = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbTextSize(int sp) {
        this.thumbTextSize = InstallmentPromptLineUtils.sp2px(sp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbTextColor(@ColorInt int color) {
        thumbTextColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showProgressInFloat() {
        this.showProgressInFloat = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder animDuration(long duration) {
        animDuration = duration;
        return this;
    }

    public InstallmentPromptLineConfigBuilder touchToSeek() {
        this.touchToSeek = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder seekBySection() {
        this.seekBySection = true;
        return this;
    }


    public InstallmentPromptLineConfigBuilder bottomSidesLabels(String[] bottomSidesLabels) {
        this.bottomSidesLabels = bottomSidesLabels;
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbBgAlpha(float thumbBgAlpha) {
        this.thumbBgAlpha = thumbBgAlpha;
        return this;
    }

    public InstallmentPromptLineConfigBuilder thumbRatio(float thumbRatio) {
        this.thumbRatio = thumbRatio;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showThumbShadow(boolean showThumbShadow) {
        this.showThumbShadow = showThumbShadow;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signColor(@ColorInt int color) {
        this.signColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signTextSize(int sp) {
        this.signTextSize = InstallmentPromptLineUtils.sp2px(sp);
        return this;
    }

    public InstallmentPromptLineConfigBuilder signTextColor(@ColorInt int color) {
        this.signTextColor = color;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showSign() {
        this.showSign = true;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signArrowHeight(int signArrowHeight) {
        this.signArrowHeight = signArrowHeight;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signArrowWidth(int signArrowWidth) {
        this.signArrowWidth = signArrowWidth;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signRound(int signRound) {
        this.signRound = signRound;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signHeight(int signHeight) {
        this.signHeight = signHeight;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signWidth(int signWidth) {
        this.signWidth = signWidth;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signBorderSize(int signBorderSize) {
        this.signBorderSize = signBorderSize;
        return this;
    }

    public InstallmentPromptLineConfigBuilder showSignBorder(boolean showSignBorder) {
        this.showSignBorder = showSignBorder;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signBorderColor(int signBorderColor) {
        this.signBorderColor = signBorderColor;
        return this;
    }

    public InstallmentPromptLineConfigBuilder signArrowAutofloat(boolean signArrowAutofloat) {
        this.signArrowAutofloat = signArrowAutofloat;
        return this;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getProgress() {
        return progress;
    }

    public boolean isFloatType() {
        return floatType;
    }

    public int getTrackSize() {
        return trackSize;
    }

    public int getSecondTrackSize() {
        return secondTrackSize;
    }

    public int getThumbRadius() {
        return thumbRadius;
    }

    public int getThumbRadiusOnDragging() {
        return thumbRadiusOnDragging;
    }

    public int getTrackColor() {
        return trackColor;
    }

    public int getSecondTrackColor() {
        return secondTrackColor;
    }

    public int getThumbColor() {
        return thumbColor;
    }

    public int getSectionCount() {
        return sectionCount;
    }

    public boolean isShowSectionMark() {
        return showSectionMark;
    }

    public boolean isAutoAdjustSectionMark() {
        return autoAdjustSectionMark;
    }

    public boolean isShowSectionText() {
        return showSectionText;
    }

    public int getSectionTextSize() {
        return sectionTextSize;
    }

    public int getSectionTextColor() {
        return sectionTextColor;
    }

    public int getSectionTextPosition() {
        return sectionTextPosition;
    }

    public int getSectionTextInterval() {
        return sectionTextInterval;
    }

    public boolean isShowThumbText() {
        return showThumbText;
    }

    public int getThumbTextSize() {
        return thumbTextSize;
    }

    public int getThumbTextColor() {
        return thumbTextColor;
    }

    public boolean isShowProgressInFloat() {
        return showProgressInFloat;
    }

    public long getAnimDuration() {
        return animDuration;
    }

    public boolean isTouchToSeek() {
        return touchToSeek;
    }

    public boolean isSeekBySection() {
        return seekBySection;
    }

    public String[] getBottomSidesLabels() {
        return bottomSidesLabels;
    }

    public float getThumbBgAlpha() {
        return thumbBgAlpha;
    }

    public float getThumbRatio() {
        return thumbRatio;
    }

    public boolean isShowThumbShadow() {
        return showThumbShadow;
    }

    public InstallmentPromptLineConfigBuilder setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public int getSignColor() {
        return signColor;
    }

    public int getSignTextSize() {
        return signTextSize;
    }

    public int getSignTextColor() {
        return signTextColor;
    }


    public boolean isshowSign() {
        return showSign;
    }

    public String getUnit() {
        return unit;
    }

    public int getSignArrowHeight() {
        return signArrowHeight;
    }

    public int getSignArrowWidth() {
        return signArrowWidth;
    }

    public int getSignRound() {
        return signRound;
    }

    public int getSignHeight() {
        return signHeight;
    }

    public int getSignWidth() {
        return signWidth;
    }

    public int getSignBorderSize() {
        return signBorderSize;
    }

    public boolean isShowSignBorder() {
        return showSignBorder;
    }

    public int getSignBorderColor() {
        return signBorderColor;
    }

    public boolean isSignArrowAutofloat() {
        return signArrowAutofloat;
    }

    public InstallmentPromptLineConfigBuilder format(NumberFormat format) {
        this.format = format;
        return this;
    }

    public NumberFormat getFormat() {
        return format;
    }

    public boolean isReverse() {
        return reverse;
    }

    public InstallmentPromptLineConfigBuilder reverse() {
        this.reverse = true;
        return this;
    }
}
