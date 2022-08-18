package Setup;

import Util.RestUtil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetupBase
{
    protected static final String appId = "efb27ef21e8d69685f831b699181833a";
    protected static final String baseUri = "https://samples.openweathermap.org/data/2.5/";


    @BeforeTest
    public void Initialize()
    {
       RestUtil.SetBaseURI(baseUri);
    }

    @AfterTest
    public void TearDown()
    {
        RestUtil.ResetBaseURI();
    }

}
