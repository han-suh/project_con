package com.artu.fullstack_team_project_application.repository.widgets;

import com.artu.fullstack_team_project_application.entity.widgets.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {

    void deleteById(Integer id);

    @Query("SELECT w FROM Widget w WHERE w.user.userId = :userId AND w.widgetIsUsed = TRUE")
    List<Widget> findWidgetsByUserIdAndWidgetIsUsedTrue(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Widget w SET w.widgetIsUsed = TRUE WHERE w.id = :widgetId AND w.user.userId = :userId")
    int updateWidgetIsUsedToTrue(@Param("widgetId") Integer widgetId, @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Widget w SET w.widgetIsUsed = FALSE WHERE w.id = :widgetId AND w.user.userId = :userId")
    int updateWidgetIsUsedToFalse(@Param("widgetId") Integer widgetId, @Param("userId") String userId);

    @Query("SELECT w.id FROM Widget w WHERE w.widgetTheme = :widgetTheme")
    List<Integer> findWidgetIdsByWidgetTheme(@Param("widgetTheme") String widgetTheme);

    @Query("SELECT w.widgetTheme FROM Widget w WHERE w.id = :widgetId AND w.user.userId = :userId")
    String findWidgetThemeByWidgetIdAndUserId(@Param("widgetId") Integer widgetId, @Param("userId") String userId);

    @Query("SELECT w.widgetSize FROM Widget w WHERE w.id = :widgetId AND w.user.userId = :userId")
    Integer findWidgetSizeByWidgetIdAndUserId(@Param("widgetId") Integer widgetId, @Param("userId") String userId);

    @Query("SELECT w.id FROM Widget w WHERE w.widgetSize = :widgetSize AND w.user.userId = :userId")
    Integer findWidgetIdByWidgetSizeAndUserId(@Param("widgetSize") Integer widgetSize, @Param("userId") String userId);
}



