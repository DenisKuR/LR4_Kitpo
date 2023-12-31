package types.users;

import comparator.Comparator;

import java.io.IOException;
import java.io.InputStreamReader;

public interface UserType {
    public String typeName();
    public Object create();
    public Object clone(Object object);
    public Object readValue(InputStreamReader in) throws IOException;
    public Object parseValue(String ss);
    public Comparator getTypeComparator();
    String toString(Object object);
}
