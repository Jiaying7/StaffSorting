import java.io.File;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadStaffData {

	public static void main(String[] args) throws Exception{
		//parsing and reading the CSV file data into the film (object) array
		// provide the path here...
        File directory = new File("./");
  		String name = directory.getAbsolutePath() + "//Staff.csv";

		// a) 使用BufferedReader读取CSV文件
		BufferedReader br = new BufferedReader(new FileReader(name));
		Staff[] staffs = new Staff[10000];

		// this will just print the header in CSV file
		br.readLine();

		int i = 0;
		String st;
		while ((st = br.readLine()) != null) {
			String[] data = st.split(",");
			staffs[i++] = new Staff(Integer.parseInt(data[0]), data[1], data[2], data[3], Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		}
		br.close();  // Closes the BufferedReader

		// We can print staff details due to overridden toString method in Staff class
		System.out.println(staffs[0]);
		System.out.println(staffs[1]);

		// We can compare staffs based on their ID due to overridden CompareTo method in Staff class
		System.out.println(staffs[0].equals(staffs[0]));
		System.out.println(staffs[0].equals(staffs[1]));

		// b) 使用递归方法计算前1000名员工工资总和
		float totalWage = calcTotalWageRecursively(staffs,  Math.min(i - 1, 999));
		System.out.println("Total wage: " + totalWage);

		// c) 使用迭代方法找到各列的最大值
		float[] maxValues = findMaxValues(staffs, i);
		System.out.println("Max EmpNo: " + (int) maxValues[0]);
		System.out.println("Max Wage: " + maxValues[1]);
		System.out.println("Max Project Completion Rate: " + maxValues[2]);

		//d) 根据用户选择的列对数组进行排序
		//请注意，这里使用了Scanner类，也可以根据需要使用Swing窗口库
		Scanner userInput = new Scanner(System.in);
		System.out.println("***Enter the column to sort by (empNo or wage or projectCompletionRate)***:");
		String sortBy = userInput.nextLine();

		sortStaffs(staffs, i, sortBy);

		System.out.println("Sorted staffs:");
		for (int j = 0; j < i; j++) {
			System.out.println(staffs[j]);
		}

		userInput.close();

		// e) 使用多线程分别根据不同列对数据副本进行排序，并将结果保存到不同的文件中
		SortThread sortThread1 = new SortThread(staffs, i, "empNo", "sortedStaffs_C1.csv");
		SortThread sortThread2 = new SortThread(staffs, i, "wage", "sortedStaffs_C5.csv");
		SortThread sortThread3 = new SortThread(staffs, i, "projectCompletionRate", "sortedStaffs_C6.csv");
		sortThread1.start();
		sortThread2.start();
		sortThread3.start();

		// 等待所有线程完成
		sortThread1.join();
		sortThread2.join();
		sortThread3.join();

		System.out.println("Sorting and saving completed.");
	}

	// b) 递归方法计算前1000名员工工资总和
	private static float calcTotalWageRecursively(Staff[] staffs, int index) {
		if (index < 0 || staffs[index].getEmpNo() > 1000) {
			return 0;
		}
		return staffs[index].getWage() + calcTotalWageRecursively(staffs, index - 1);
	}

	// c) 迭代方法找到各列的最大值
	private static float[] findMaxValues(Staff[] staffs, int length) {
		float maxEmpNo = 0;
		float maxWage = 0;
		float maxProjectCompletionRate = 0;

		for (int i = 0; i < length; i++) {
			Staff staff = staffs[i];
			maxEmpNo = Math.max(maxEmpNo, staff.getEmpNo());
			maxWage = Math.max(maxWage, staff.getWage());
			maxProjectCompletionRate = Math.max(maxProjectCompletionRate, staff.getProjectCompletionRate());
		}

		return new float[]{maxEmpNo, maxWage, maxProjectCompletionRate};
	}


	//根据员工编号或工资或工程完成率排序(默认为员工编号)
	public static void sortStaffs(Staff[] staffs, int count, String sortBy) {
		InsertionSort.quickInsertionSort(staffs, 0, count - 1, sortBy);
	}

}

class Staff implements Comparable<Object>{
	private int empNo;
	private String fName;
	private String sName;
	private String department;
	private float wage;
	private float projectCompletionRate;

	// constructor
	public Staff(int empNo, String fName, String sName, String department, float wage, float projectCompletionRate) {
		super();
		this.empNo = empNo;
		this.fName= fName;
		this.sName= sName;
		this.department= department;
		this.wage = wage;
		this.projectCompletionRate = projectCompletionRate;
	}

	// setters and getters
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public float getProjectCompletionRate() {
		return projectCompletionRate;
	}

	public void setProjectCompletionRate(float projectCompletionRate) {
		this.projectCompletionRate = projectCompletionRate;
	}

	public float getWage(){
		return wage;
	}

	public void setWage(float wage){
		this.wage = wage;
	}

		// so the film objects can be compared when sorting/searching
	// NOTE: this will only allow comparisons based on the title of the film
	@Override
	public int compareTo(Object obj) {
		Staff sff = (Staff)obj;
		return empNo - (sff.getEmpNo());
	}

	public int compare(Staff sff, String sortBy) {
		switch (sortBy) {
			case "wage":
				return Float.compare(this.wage, sff.getWage());
			case "projectCompletionRate":
				return Float.compare(this.projectCompletionRate, sff.getProjectCompletionRate());
			case "empNo":
			default:
				return compareTo(sff);
		}
	}

	@Override
	public String toString() {
		return "Staff [EmpNo=" + empNo + ", first name=" + fName+ ", last Name=" + sName+ ", department="
				+ department+  ", wage=" + wage+ ", project completion rate="
				+ projectCompletionRate+ "]";
	}



}
