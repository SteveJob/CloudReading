package com.kongshanxinyu.cloudreading.enumpkg;

/**
 * Created by Steve on 16/10/11.
 * E-mail: singleframe@aliyun.com
 */
public enum PlayMode {
    SERIAL(0),LOOP(1),RANDOM(2);

    private int value;

    PlayMode(int value) {
        this.value = value;
    }

    public int value(){
        return value;
    }

    public static PlayMode modeOf(int value){
        switch (value){
            case 0:
                return SERIAL;
            case 1:
                return LOOP;
            case 2:
                return RANDOM;
            default:
                return null;
        }
    }
}
