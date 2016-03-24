package excel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

public class Test4
{

	public static void main(String[] args) throws IOException
	{
		String path = "E:/01_work/02_cec/03_需求/04_NGC/06_NGC凌晨较慢问题/local_acc_13.log";
		String path2 = "E:/01_work/02_cec/03_需求/04_NGC/06_NGC凌晨较慢问题/cata_13.log";
		
		List<String> list = FileUtils.readLines(new File(path));
		List<String> list2 = FileUtils.readLines(new File(path2));
		
		int count = 0;
		for (String str : list)
		{
			StringTokenizer st = new StringTokenizer(str);
			while (st.hasMoreTokens())
			{
				String aa = st.nextToken();
				if (aa.contains("lockSeats.xml"))
				{
//					System.out.println(aa);
					String[] arr = aa.split("&");
//					System.out.println(arr[1].substring(arr[1].indexOf("=") + 1));
//					System.out.println(arr[2].substring(arr[2].indexOf("=") + 1));
					
					String channelOrderCode = arr[1].substring(arr[1].indexOf("=") + 1);
					String channelShowCode = arr[2].substring(arr[2].indexOf("=") + 1);
					String seatCodes = arr[3].substring(arr[3].indexOf("=") + 1);
					
					boolean flag = false;
					for (String ttt : list2)
					{
						if (ttt.contains(channelOrderCode))
						{
//							System.out.println(ttt);
							flag = true;
							break;
						}
					}
					if (!flag)
					{
						System.out.println(aa);
						count++;
					}
				}
			}	
		}
		System.out.println(count);
	}

}
