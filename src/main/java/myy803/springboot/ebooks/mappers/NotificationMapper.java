package myy803.springboot.ebooks.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.ebooks.models.Notification;

public interface NotificationMapper extends JpaRepository<Notification, Integer>{

}
