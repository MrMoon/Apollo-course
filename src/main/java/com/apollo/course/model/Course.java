package com.apollo.course.model;

import lombok.Data;

import java.util.*;

@Data
public class Course {

    private boolean isPublic, isActive = true;
    private EnrollmentType courseEnrollmentType = EnrollmentType.PUBLIC;
    private String courseId = UUID.randomUUID().toString();
    private HashSet<Chapter> courseChapters = new HashSet<>();
    private Date courseDateOfCreation = Calendar.getInstance().getTime();
    private HashSet<String> courseOwners = new HashSet<>(), courseMembers = new HashSet<>();
    private String courseName = this.courseId + '-' + this.courseDateOfCreation, courseType, courseDescription, courseCategory, courseRoomId;

    public boolean doesNotHaveOwner(String ownerId) {
        return !this.courseOwners.contains(ownerId);
    }

    public Optional<Chapter> getChapter(Chapter chapter) {
        return this.courseChapters.stream().filter(chapterTemp -> chapterTemp.getChapterId().equals(chapter.getChapterId())).findFirst();
    }

    public Set<String> getAllCourseMembers() {
        HashSet<String> allCourseMembers = new HashSet<>(this.courseMembers);
        allCourseMembers.addAll(this.courseOwners);
        return allCourseMembers;
    }

    public void removeChapter(Chapter chapter) {
        this.courseChapters.remove(chapter);
    }

    public Course addMember(String memberId) {
        this.courseMembers.add(memberId);
        return this;
    }

    public Boolean addMembers(HashSet<String> membersIds) {
        return this.courseMembers.addAll(membersIds);
    }

    public Boolean addOwners(HashSet<String> ownersIds) {
        return this.courseOwners.addAll(ownersIds);
    }

    public Course addOwner(String ownerId) {
        this.courseOwners.add(ownerId);
        return this;
    }

    public Course addChapter(Chapter chapters) {
        this.courseChapters.add(chapters);
        return this;
    }

}
