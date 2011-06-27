package NMLab.team10.rollingthecheese.gameSetting;

public class cZipObject implements java.io.Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -2325258638661797618L;
    private int iOriginalSize;
    private byte[] compressData;

    public cZipObject()
    {
        iOriginalSize = 0;
    }

    public synchronized void setData(byte[] inData, int
            iOrigSize)
    {
        compressData = inData;
        iOriginalSize = iOrigSize;
    }

    public synchronized byte[] getData()
    {
        return compressData;
    }

    public synchronized int getOriginalSize()
    {
        return iOriginalSize;
    }
}


