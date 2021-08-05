/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.db.entity.Favorite;
import com.ssafy.db.repository.FavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FavService")
public class FavServiceImpl implements FavService{
    @Autowired
    FavRepository favRepository;

    @Override
    public Favorite favorite(Integer userIdx){
        return null;
    };

    @Override
    public boolean toggleFav(String nickname){
        return true;
    };

    @Override
    public boolean checkFav(String nickname){
        return true;
    };

    @Override
    public boolean getFavList(String nickname){
        return true;
    };
}
