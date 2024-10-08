import java.io.FileWriter;
import java.io.IOException;

class SortThread extends Thread {
    private Staff[] staffs;
    private int staffCount;
    private String sortBy;
    private String fileName;

    public SortThread(Staff[] staffs, int staffCount, String sortBy, String fileName) {
        this.staffs = staffs.clone(); // 创建数组的副本，以便每个线程独立排序
        this.staffCount = staffCount;
        this.sortBy = sortBy;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        ReadStaffData.sortStaffs(staffs, staffCount, sortBy);
        saveSortedStaffsToFile(staffs, staffCount, fileName);
    }

    private void saveSortedStaffsToFile(Staff[] staffs, int staffCount, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("EmpNo,First Name,Last Name,Department,Wage,Project Completion Rate\n");
            for (int i = 0; i < staffCount; i++) {
                Staff staff = staffs[i];
                fileWriter.write(staff.getEmpNo() + "," + staff.getFName() + "," + staff.getSName() + "," + staff.getDepartment() + "," + staff.getWage() + "," + staff.getProjectCompletionRate() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
