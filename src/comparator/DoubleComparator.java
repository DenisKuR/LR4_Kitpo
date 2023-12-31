package comparator;

import types.DoubleType;

import java.io.Serializable;

public class DoubleComparator implements Comparator, Serializable {
    @Override
    public double compare(Object o1, Object o2) {
        return ((DoubleType)o1).getDoubleTypeValue() - ((DoubleType)o2).getDoubleTypeValue();
    }
}
