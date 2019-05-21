// IServiceAidlInterface.aidl
package pk.wei.com.appserice;

// Declare any non-default types here with import statements

interface AppServiceRemoteBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setData(String s);
}
