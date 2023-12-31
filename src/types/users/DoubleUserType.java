package types.users;

import comparator.Comparator;
import comparator.DoubleComparator;
import types.DoubleType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class DoubleUserType implements UserType{

    private final double MAX = 1000.0;
    private final double MIN = -1000.0;

    @Override
    public String typeName() {
        return "Double";
    }

    @Override
    public Object create() {
        Random random = new Random();
        DoubleType doubleTypeValue = new DoubleType(random.nextDouble((MAX - MIN)) + MIN);
        return doubleTypeValue;
    }

    @Override
    public Object clone(Object object) {
        DoubleType copyDoubleTypeValue = new DoubleType(((DoubleType)object).getDoubleTypeValue());
        return copyDoubleTypeValue;
    }

    @Override
    public Object readValue(InputStreamReader in) {
        try {
            return in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DoubleType parseValue(String doubleString) {
        return new DoubleType(Double.parseDouble(doubleString));
    }


    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new DoubleComparator();
        return comparator;
    }

    @Override
    public String toString(Object object) {
        return object.toString();
    }
}
