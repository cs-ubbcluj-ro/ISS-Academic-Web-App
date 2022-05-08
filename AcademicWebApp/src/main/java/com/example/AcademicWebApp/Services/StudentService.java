package com.example.AcademicWebApp.Services;
import com.example.AcademicWebApp.Models.*;
import com.example.AcademicWebApp.Repositories.CourseRepo;
import com.example.AcademicWebApp.Repositories.FacultyRepo;
import com.example.AcademicWebApp.Repositories.GroupRepo;
import com.example.AcademicWebApp.Repositories.StudentRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@NoArgsConstructor
@Service("studentService")
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    private FacultyRepo facultyRepo;
    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private CourseRepo courseRepo;

    public Student saveStudent(StudentData student)
    {
        Integer fid1 = facultyRepo.findFidByName(student.getFaculty1());
        Integer year1 = student.getYear1();
        Integer fid2 = facultyRepo.findFidByName(student.getFaculty2());
        Integer year2 = student.getYear2();
        String username = student.getUsername();
        String name = student.getName();
        //we must get the groups
        List<Integer> groups1 = groupRepo.findAllGidsByFacultyAndYear(fid1, year1);
        List<Integer> groups2 = groupRepo.findAllGidsByFacultyAndYear(fid2, year2);
        Random rand = new Random();
        Integer group1 = groups1.get(rand.nextInt(groups1.size()));
        Integer group2;
        if(groups2.size() == 0)
            group2 = null;
        else
            group2 = groups2.get(rand.nextInt(groups2.size()));

        Student newS = new Student(username, name, group1, group2);
        studentRepository.save(newS);
        return newS;
    }

    public List<Faculty> getFaculties()
    {
        List<Faculty> listOfFaculties = facultyRepo.findAll();
        return listOfFaculties;
    }

    //getOptionalsForFirstGroup
    //we initially get the username of the student
    //we get its first  group
    //we get the faculty and the year based on the group
    //we get all courses for that specific group, base on faculty and year
    //this is for the curricullum, but if we add the priority 2 we get the optionals

    public List<Course> getCoursesForFirstGroup(String username)
    {
        Student s = studentRepository.getById(username);
        Integer firstGroup = s.getGroup1();
        Group group = groupRepo.getById(firstGroup);
        Integer fid = group.getFaculty();
        Integer year = group.getYear();
        List<Course> courses = courseRepo.findCoursesByFidAndYear(fid, year);
        return courses;
    }

    public List<Course> getCoursesForSecondGroup(String username)
    {
        Student s = studentRepository.getById(username);
        Integer secondGroup = s.getGroup2();
        if(secondGroup == null)
            return null;
        Group group = groupRepo.getById(secondGroup);

        Integer fid = group.getFaculty();
        Integer year = group.getYear();
        List<Course> courses = courseRepo.findCoursesByFidAndYear(fid, year);
        return courses;
    }

    //we get the username,a name, a faculty and a year, eventually 2 faculties
    //firstly -> get the group (gid)
    //we need the fid and the year
    //we get fid by Select fid from faculty where name = facultyName
    //we return a student entity?

}
