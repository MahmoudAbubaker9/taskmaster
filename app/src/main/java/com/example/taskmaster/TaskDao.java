//package com.example.taskmaster;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface TaskDao {
//    @Query("SELECT * FROM task")
//    List<Task> getAll();
//
//    @Query("SELECT * FROM task WHERE id IN (:userIds)")
//    List<Task> loadAllByIds(int[] userIds);
//
//
//
//    @Insert
//    void savedata(Task task);
//
//
//    @Delete
//    void delete(Task user);
//}
//
