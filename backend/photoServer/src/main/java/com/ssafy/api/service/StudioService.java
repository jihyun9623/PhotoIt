/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.response.StudioGetPhotosResBody;
import com.ssafy.api.response.StudioPgProfileResBody;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface StudioService {
    public StudioPgProfileResBody getPgProfile(String nickname);

    public String[] showCalendar(String nickname);

    public boolean addCalendar(String nickname, String JWT, String[] cal_time);

    public boolean deleteCalendar(String nickname, String JWT, String[] cal_time);

    public StudioGetPhotosResBody getBestPhotos(String nickname);

    public StudioGetPhotosResBody getAllPgPhotos(String nickname);
}
