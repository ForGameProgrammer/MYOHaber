package com.forgameprogrammer.myo.myohaber.Classes;

import java.util.Comparator;

/**
 * Created by The Flash on 02/05/2016.
 */
public class DersSiralayici implements Comparator<Program>
{
    @Override
    public int compare(Program p1, Program p2)
    {
        return p1.getDerssira() < p2.getDerssira() ? -1 : p1.getDerssira() > p2.getDerssira() ? 1 : 0;
    }
}
