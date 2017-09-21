package yxinfo.core.service.ou;

/**
 * Created by dy on 2017/9/20.
 */
public interface MemberMajorService {

    /**
     * 添加用户专长
     *
     * @param memberId
     * @param major
     */
    void addMemberMajor( Integer memberId, String major );

    /**
     * 删除用户专长
     *
     * @param memberId
     */
    void deleteMemberMajor( Integer memberId );
}
