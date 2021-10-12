# Kotlin SemiCircleProgressBar
The project is a simple custom view that shows the semi-circle progress bar from an extended View.
## Usage
- In XML Layout
```xml
<vic.sample.semicircleprogressbar.custom.SemiCircleProgressBar
    android:id="@+id/SemiCircleProgressBar"
    android:layout_width="300dp"
    android:layout_height="200dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:position="TOP"
    app:progressClockwise="true"
    app:currentProgress="30"
    app:progressColor="@color/black"
    app:progressHolderColor="@color/teal_200"
    app:progressHolderWidth="36"
    app:progressWidth="24" />
```
- All customizable attributes:
```xml
<declare-styleable name="SemiCircleProgressBar">
    <attr name="maxProgress" format="integer" />
    <attr name="minProgress" format="integer" />
    <attr name="currentProgress" format="integer" />
    <attr name="progressColor" format="color" />
    <attr name="progressHolderColor" format="color" />
    <attr name="progressWidth" format="integer" />
    <attr name="progressHolderWidth" format="integer" />
    <attr name="progressClockwise" format="boolean" />
    <attr name="position" format="enum">
        <enum name="TOP" value="0" />
        <enum name="BOTTOM" value="1" />
        <enum name="LEFT" value="2" />
        <enum name="RIGHT" value="3" />
    </attr>
</declare-styleable>
```
## The UI Flow
<br>
<img src="https://github.com/KeithWang/Kotlin-SemiCircleProgressBar/blob/master/pic/flow.gif?raw=true" height="530" width="300" />
