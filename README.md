# Multithreaded Array and Staff Sorting System

This Java project showcases a variety of sorting algorithms and techniques to handle staff data and arrays. The project also demonstrates the use of multithreading for parallel processing and sorting of large datasets.

## Project Structure

- **ArraySorter.java**: This class implements sorting algorithms to remove multiples of five from arrays and sort the remaining elements. It also includes functionality to sort arrays by their lengths.
- **ArraySorterThread.java**: A multithreaded version of the `ArraySorter`, where each array is processed in a separate thread. This allows parallel processing to improve performance on large datasets.
- **ArraySorterThreadTest.java**: A test class to compare the performance of single-threaded and multi-threaded sorting algorithms on arrays of different sizes.
- **InsertionSort.java**: Implements both insertion sort and quicksort algorithms, using an iterative approach to avoid stack overflow issues for large datasets.
- **ReadStaffData.java**: Reads staff data from a CSV file, calculates total wages recursively, finds maximum values for various attributes, and allows sorting based on different columns such as employee number, wage, or project completion rate.
- **SortThread.java**: A thread-based class that sorts the staff data based on a given attribute and saves the sorted results to a CSV file.
- **Staff.java**: Represents a staff member with attributes like employee number, name, department, wage, and project completion rate. It provides functionality for comparison and sorting based on these attributes.
- **Staff.csv**: The data file containing staff information including employee number, first and last names, department, wage, and project completion rate.

## Features

### Array Operations:
- **Remove Multiples of Five**: Filters out multiples of five from each array before sorting.
- **Array Sorting**: Sorts arrays using selection sort and sorts arrays by their length.
- **Multithreading**: Implements parallel array processing using Java's `ExecutorService` to improve performance on large datasets.

### Staff Data Operations:
- **Recursive Wage Calculation**: Calculates the total wages for the first 1000 employees using recursion.
- **Max Values Calculation**: Iteratively finds the maximum values for employee number, wage, and project completion rate.
- **Sorting by Attributes**: Allows sorting staff data based on employee number, wage, or project completion rate.
- **Multithreaded Sorting and Saving**: Sorts the staff data in parallel by different attributes and saves the results to separate CSV files.

## How to Run

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/YOUR_USERNAME/Multithreaded-Array-Staff-Sorting.git
    ```
2. **Navigate to the Directory**:
    ```bash
    cd Multithreaded-Array-Staff-Sorting
    ```
3. **Compile and Run**:
    Compile the Java files and run the `Main` or `Test` class to execute the project.
    ```bash
    javac ArraySorter.java ArraySorterThread.java Staff.java ReadStaffData.java
    java ArraySorter
    ```

## Example Usage
- **Single-threaded sorting**:
    ```bash
    java ArraySorter
    ```
- **Multithreaded sorting**:
    ```bash
    java ArraySorterThread
    ```

The results will print sorted arrays or staff information, depending on the selected class.

## Technologies
- **Language**: Java
- **Concepts**: Multithreading, Sorting Algorithms, Object-Oriented Programming (OOP)
- **Tools**: Java ExecutorService, CSV file handling

## License
This project is licensed under the MIT License.
