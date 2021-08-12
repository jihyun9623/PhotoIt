/* 작성자 : 김지현 */

package com.ssafy.api.service;

import com.ssafy.db.entity.Favorite;
import com.ssafy.db.entity.MyStudio;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.FavRepository;
import com.ssafy.db.repository.MyStudioRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service("FavService")
public class FavServiceImpl implements FavService{
    @Autowired
    UserRepository userRepository;
    MyStudioRepository myStudioRepository;
    FavRepository favRepository;

    // 찜 추가
    @Override
    public boolean addFav(String userNick, String pgNick){
        // 유저, 마이스튜디오
        User user = userRepository.findUserByNickname(userNick);
        MyStudio myStudio = myStudioRepository.findByUser_Nickname(pgNick);
        // nickname으로 user idx 리턴
        int userIdx = user.getIdx();
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudio.getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        // 일치하는 studioIdx가 없다면 list에 추가
        for(Iterator<Favorite> it = fav.iterator(); it.hasNext(); ){
            Favorite value = it.next();
            // 찜 리스트에 있음
            if(value.getMyStudio().getIdx()==studioIdx) {
                return false;
            }
        }
        // 찜 리스트에 없음
        Favorite favorite = new Favorite(0, user, myStudio);
        favRepository.save(favorite);
        return true;
    };

    // 찜 삭제
    @Override
    public boolean deleteFav(String userNick, String pgNick){
        // 유저, 마이스튜디오
        User user = userRepository.findUserByNickname(userNick);
        MyStudio myStudio = myStudioRepository.findByUser_Nickname(pgNick);
        // nickname으로 user idx 리턴
        int userIdx = user.getIdx();
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudio.getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        // 일치하는 studioIdx가 있다면 list에서 삭제
        for(Iterator<Favorite> it = fav.iterator(); it.hasNext(); ){
            Favorite value = it.next();
            // 찜 리스트에 있음
            if(value.getMyStudio().getIdx()==studioIdx) {
                return favRepository.deleteByMyStudio_Idx(studioIdx);
            }
        }

        return false;
    };

    // 찜 확인
    @Override
    public boolean checkFav(String userNick, String pgNick){
        // nickname으로 user idx 리턴
        int userIdx = userRepository.findUserByNickname(userNick).getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);
        // pgNick으로 studioIdx 가져오기
        int studioIdx = myStudioRepository.findByUser_Nickname(pgNick).getIdx();

        if(fav.size()==0) return false;

        // 일치하는 studioIdx가 있다면 true
        for(Favorite f : fav){
            if(f.getMyStudio().getIdx()==studioIdx) return true;
        }
        return false;
    };

    // 찜 리스트 불러오기
    @Override
    public Integer[] getFavList(String userNick){
        // nickname으로 user idx 리턴
        int userIdx = userRepository.findUserByNickname(userNick).getIdx();

        // userIdx로 찜목록 불러오기
        List<Favorite> fav = favRepository.findByUser_Idx(userIdx);

        if(fav.size()==0) return null;

        // 매핑
        Integer[] favList = new Integer[fav.size()];
        int i=0;
        for(Favorite f : fav) favList[i++] = f.getMyStudio().getIdx();

        return favList;
    };
}
