import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class CalEvent1 {
	public static void main(String[] args) throws IOException {

		String ver = "VERSION:", pub = "PUBLIC";
		double verNum = 2.0;
		String tz = "", dtstart = "", dtend = "";
		String location = "", summary = "", input = "", cl = "", priority = "";
		
		int lineNum = 0;
		String line = "";
		int arrayNum = 100;
		int[] newEndTime = new int[arrayNum];
		int[] newStartTime = new int[arrayNum];
		String[] startTime;
		String[] endTime;
		int fileCount;
		int countS = 1;
		int countE = 0;
		String firstnewStartTime = "000000";
		String lastnewEndTime = "240000";
		String sTimeZone = "";
		String eTimeZone = "";

		ver = ver.concat(verNum + "\n");

		//File file1, file2;
		File file1;

		for(int i = 0; i < arrayNum; i++) {
			newStartTime[i] = 250000;
			newEndTime[i] = 250000;
		}

		// Get the number of files
		fileCount = args.length;
		//System.out.println("File count: " + fileCount);

		// Check that you have more than one file
		if(fileCount <= 1) {
			System.out.println("Error: You need more than one file.");
		}

		for(int i = 0; i < fileCount; i++) {
			file1 = new File(args[i]);

			// Get contents of the first file
			Scanner scan = new Scanner(file1);
		
			// Find the DTSTART and DTEND so that we can use it to compare
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				lineNum++;

				// New End Time
				if(line.contains("DTSTART;TZID=")) {
					//Copy one start date and replace the time
					newEndTime[countE+1] = Integer.parseInt("240000");

					// Get the end time zone so you can compare
					eTimeZone = line.substring(8,line.length()-6);
					
					// Replace STSTART to DTEND
					newEndTime[countE] = Integer.parseInt(line.substring(39,line.length()));
					//System.out.println("New: " + countE + " "+ newEndTime[countE]);
					countE++;
				}
				// New Start Time
				if(line.contains("DTEND;TZID=")) {
					//Copy one end date and replace the time
					newStartTime[0] = Integer.parseInt("000000");

					// Get the start time zone so you can compare
					sTimeZone = line.substring(6,line.length()-6);
					
					// Replace STSTART to DTEND
					newStartTime[countS] = Integer.parseInt(line.substring(37,line.length()));
					//System.out.println("New: " + countS + " "+ newStartTime[countS]);
					countS++;
				}
			}
		}

		// Check to see what the time zones are
		//System.out.println(eTimeZone);
		//System.out.println(sTimeZone);

		// Compare the time zones
		if(eTimeZone.equals(sTimeZone)) {
		}
		else {
			System.out.println("Error: Different time zones.");
		}

		// Test to see what's in the array
		/*for(int i = 0; i < countE; i++) {
			//System.out.println(newStartTime[i]);
			//System.out.println(newEndTime[i]);
		}*/

		// Sort the array of times
		Arrays.sort(newStartTime);
		Arrays.sort(newEndTime);


		startTime = new String[countS];
		endTime = new String[countS];

		// Copy int array into string array
		for(int i = 0; i < countS; i++) {
			startTime[i] = Integer.toString(newStartTime[i]);

			endTime[i] = Integer.toString(newEndTime[i]);
		}

		// Check to make sure that the time is 6 digits long
		for(int i = 0; i < countS; i++){
			if(startTime[i].length() < 6) {
				for(int a = 0; a < 5; a++) {
					//System.out.println("Add: " + startTime[i]);
					// Add zero to the front of the time if the amount of digits is less than 6
					startTime[i] = startTime[i].substring(0,0) + "0" + startTime[i].substring(0);
				}
			}
			if(endTime[i].length() < 6) {
				for(int a = 0; endTime[i].length() < 6; a++) {
					//System.out.println("Add: " + endTime[i]);
					// Add zero to the front of the time if the amount of digits is less than 6
					endTime[i] = endTime[i].substring(0,0) + "0" + endTime[i].substring(0);
				}
			}
		}
		
		// Check that it is sorted
		/*for(int i = 0; i < countS; i++) {
			System.out.println("Sorted: " + endTime[i]);
			System.out.println("Sorted: " + startTime[i]);
		}*/


		// Check for duplicate start and end time
		/*int count1 = 0;
		int count2 = 0;
		for(int i = 0; i < countS; i++) {
			if(i != k) {
				startTime[count1] = Integer.toString(newStartTime[i]);
				count1++;
			}
			else{
				i++;
				countS--;
				//startTime[count1] = Integer.toString(newStartTime[i]);
			}
		}

		for(int i = 0; i < countS; i++) {
			if(i != l) {
				endTime[i] = Integer.toString(newEndTime[i]);
			}
			else{
				i++;
				endTime[i] = Integer.toString(newEndTime[i]);
			}
		}*/

		try {
			// Create a new file
			File file = new File("Free Time.ics");

			// Writing to the ics file
			// FileWriter will append to the file
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			// Writing to the file
			output.write("BEGIN:VCALENDAR\n");
			output.write(ver);
			for(int i = 0; i < (countE + 1); i++) {
				output.write("BEGIN:VEVENT\n");
				output.write("DTSTART;" + eTimeZone + startTime[i] + "\n");
				output.write("DTEND;" + eTimeZone + endTime[i] + "\n");
				output.write("LOCATION: Undecided\n");
				output.write("SUMMARY: Free Time\n");
				output.write("CLASS:" + pub + "\n");
				output.write("PRIORITY:0\n");
				output.write("END:VEVENT\n");
			}

			output.write("END:VCALENDAR");
			output.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
