# LuckyWheel view
[![](https://jitpack.io/v/sourena21/vafinoluckywheel.svg)](https://jitpack.io/#sourena21/vafinoluckywheel)

یه ویو ساده برای استفاده از گردونه شانس توی اپ های اندرویدی. برای اینکه بتونید از لایبرری استفاده کنید تیکه کد زیرو رو به buil.gradle پروژه اضافه کنید:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
و تیکه کد زیر رو به build.gradle ماژول app اضافه کنید:
```
dependencies {
	        implementation 'com.github.sourena21:vafinoluckywheel:Tag'
	}
```
کد ورژن رو از بالا بردارین و با Tag توی لینک جا به جا کنید و بعد سینک کنید.

## Usage in code

توی لایه xml
```
    <hseify69.ir.vafinoluckywheel.LuckyWheelView
        android:id="@+id/id"
        android:layout_width="match_parent"
        android:layout_height="400dp" //tarjihas size literal bashe
        .../>
```
توی کد های جاوا

```
LuckyWheelView luckyWheelView = findViewById(R.id.id);

luckyWheelView.setItemsList(/* luck items list */);
OR
luckyWheelView.startRoration();

luckyWheelView.setOnRotationResult(new OnResult() {
            @Override
            public void onSelectedLuck(LuckItem luckItem) {
                
            }
        });
```