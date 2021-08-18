/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.api.response.FavResBody;
import com.ssafy.db.entity.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FavService {
    public boolean addFav(String JWT, String userNick, String pgNick);

    public boolean deleteFav(String JWT, String userNick, String pgNick);

    public boolean checkFav(String JWT, String userNick, String pgNick);

    public FavResBody getFavList(String userNick);
}
