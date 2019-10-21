package com.integration.springbootjpa.repository.service.impl;

import com.integration.springbootjpa.entity.QSysAuthority;
import com.integration.springbootjpa.entity.QSysRole;
import com.integration.springbootjpa.entity.QSysRoleAuthority;
import com.integration.springbootjpa.entity.QSysUsers;
import com.integration.springbootjpa.entity.QSysUsersRole;
import com.integration.springbootjpa.entity.SysUsers;
import com.integration.springbootjpa.entity.SysUsersRole;
import com.integration.springbootjpa.entity.UserDTO;
import com.integration.springbootjpa.repository.SysUsersRepository;
import com.integration.springbootjpa.repository.service.SysUsersRepositoryService;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.MapExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUsersRepositoryServiceImpl implements SysUsersRepositoryService {
    @Resource
    private SysUsersRepository sysUsersRepository;

    @Override
    public Page<SysUsers> findPageListByCondition(SysUsers sysUsers, Pageable pageable) {

      /*  return sysUsersRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //equal 示例
            if (StringUtils.isNotBlank(sysUsers.getLoginName())){
                predicates.add(cb.equal(root.get("loginName"),sysUsers.getLoginName()));
            }
            //like 示例
            if (StringUtils.isNotBlank(sysUsers.getRealName())){
                predicates.add(cb.like(root.get("realName"),"%"+sysUsers.getRealName()+"%"));
            }
            //between 示例
            if (sysUsers.getAddTimeStart()!=null && sysUsers.getAddTimeEnd()!=null) {
                Predicate agePredicate = cb.between(root.get("addTime"), sysUsers.getAddTimeStart(), sysUsers.getAddTimeEnd());
                predicates.add(agePredicate);
            }
            //greaterThan 大于等于示例
            if (sysUsers.getUserState()!=null){
                predicates.add(cb.greaterThan(root.get("userState"),sysUsers.getUserState()));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        }, pageable);*/

        return null;

    }

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    //////////////////////////以下展示使用原生的dsl/////////////////////

    /**
     * 根据用户名和密码查找（假定只能找出一条）
     *
     * @param loginName
     * @param password
     * @return
     */
    public SysUsers findByUsernameAndPassword(String loginName, String password) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        return jpaQueryFactory
                .selectFrom(sysUsers)
                .where(
                        sysUsers.loginName.eq(loginName),
                        sysUsers.loginPwd.eq(password)
                )
                .fetchOne();
    }

    /**
     * 查询所有的实体,根据uIndex字段排序
     *
     * @return
     */
    public List<SysUsers> findAll() {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        return jpaQueryFactory
                .selectFrom(sysUsers)
                .orderBy(
                        sysUsers.addTime.asc()
                )
                .fetch();
    }

    /**
     * 分页查询所有的实体,根据uIndex字段排序
     *
     * @return
     */
    public QueryResults<SysUsers> findAllPage(Pageable pageable) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        return jpaQueryFactory
                .selectFrom(sysUsers)
                .orderBy(
                        sysUsers.addTime.asc()
                )
                .offset(pageable.getOffset())   //起始页
                .limit(pageable.getPageSize())  //每页大小
                .fetchResults();    //获取结果，该结果封装了实体集合、分页的信息，需要这些信息直接从该对象里面拿取即可
    }

    /**
     * 根据起始日期与终止日期查询
     *
     * @param start
     * @param end
     * @return
     */
    public List<SysUsers> findByBirthdayBetween(Date start, Date end) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        return jpaQueryFactory
                .selectFrom(sysUsers)
                .where(
                        sysUsers.addTime.between(start, end)
                )
                .fetch();
    }

    /**
     * 部分字段映射查询
     * 投影为UserRes,lambda方式(灵活，类型可以在lambda中修改)
     *
     * @return
     */
    public List<UserDTO> findAllUserDto(Pageable pageable) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        List<UserDTO> dtoList = jpaQueryFactory
                .select(
                        sysUsers.loginName,
                        sysUsers.guid,
                        sysUsers.loginName,
                        sysUsers.addTime
                )
                .from(sysUsers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(tuple -> UserDTO.builder()
                        .loginName(tuple.get(sysUsers.loginName))
                        .nickname(tuple.get(sysUsers.loginName))
                        .userId(tuple.get(sysUsers.guid).toString())
                        .birthday(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tuple.get(sysUsers.addTime)))
                        .build()
                )
                .collect(Collectors.toList());

        return dtoList;
    }

    /**
     * 部分字段映射查询
     * 投影为UserRes，自带的Projections方式,不够灵活，不能转换类型，但是可以使用as转换名字
     *
     * @return
     */
    public List<UserDTO> findAllDto2() {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        List<UserDTO> dtoList = jpaQueryFactory
                .select(
                        Projections.bean(
                                UserDTO.class,
                                sysUsers.loginName ,
                                sysUsers.guid,
                                sysUsers.loginName,
                                sysUsers.addTime
                        )
                )
                .from(sysUsers)
                .fetch();
        return dtoList;
    }

    //////////////////////////以下展示使用与SpringDataJPA整合的dsl/////////////////////

    /**
     * 根据昵称与用户名查询，并且根据uIndex排序
     *
     * @param loginName
     * @return
     */
    public List<SysUsers> findByNicknameAndUsername(String loginName, String loginName2) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        List<SysUsers> users = (List<SysUsers>) sysUsersRepository.findAll(
                sysUsers.loginName.eq(loginName).and(sysUsers.loginName.eq(loginName2)),
                sysUsers.addTime.asc()   //排序参数
        );
        return users;
    }

    /**
     * 统计名字像likeName的记录数量
     *
     * @return
     */
    public long countByNickNameLike(String likeName) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        return sysUsersRepository.count(
                sysUsers.loginName.like("%" + likeName + "%")
        );
    }

    //////////////////////////展示dsl动态查询////////////////////////////////

    /**
     * 所有条件动态分页查询
     *
     * @param loginName
     * @param password
     * @param loginName
     * @param addTime
     * @param addTime
     * @return
     */
    public Page<SysUsers> findByUserProperties(Pageable pageable, String loginName, String password, String loginName2, Date addTime, Date addTime2) {
        QSysUsers sysUsers = QSysUsers.sysUsers;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = sysUsers.isNotNull().or(sysUsers.isNull());
        //执行动态条件拼装
        predicate = loginName == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginName.eq(loginName));
        predicate = password == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginPwd.eq(password));
        predicate = loginName2 == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginName.eq(loginName2));
        predicate = addTime == null ? predicate : ExpressionUtils.and(predicate, sysUsers.addTime.eq(addTime));
        predicate = addTime2 == null ? predicate : ExpressionUtils.and(predicate, sysUsers.addTime.eq(addTime2));

        Page<SysUsers> page = sysUsersRepository.findAll(predicate, pageable);
        return page;
    }

    /**
     * 动态条件排序、分组查询
     *
     * @param loginName
     * @param password
     * @param loginName
     * @param addTime
     * @param addTime
     * @return
     */
    public List<SysUsers> findByUserPropertiesGroupByUIndex(String loginName, String password, String loginName2, Date addTime, Date addTime2) {

        QSysUsers sysUsers = QSysUsers.sysUsers;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = sysUsers.isNotNull().or(sysUsers.isNull());
        //执行动态条件拼装
        predicate = loginName == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginName.eq(loginName));
        predicate = password == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginPwd.eq(password));
        predicate = loginName2 == null ? predicate : ExpressionUtils.and(predicate, sysUsers.loginName.eq(loginName2));
        predicate = addTime == null ? predicate : ExpressionUtils.and(predicate, sysUsers.addTime.eq(addTime));
        predicate = addTime2 == null ? predicate : ExpressionUtils.and(predicate, sysUsers.addTime.eq(addTime2));
        //执行拼装好的条件并根据userId排序，根据uIndex分组
        List<SysUsers> list = jpaQueryFactory
                .selectFrom(sysUsers)
                .where(predicate)               //执行条件
                .orderBy(sysUsers.addTime.asc())     //执行排序
                .groupBy(sysUsers.addTime)           //执行分组
//                .having(sysUsers.addTime.longValue().max().gt(7))//uIndex最大值小于7
                .fetch();

        //封装成Page返回
        return list;
    }
    public List<SysUsersRole> getFileList() {
        QSysRole sysRole = QSysRole.sysRole;
        QSysUsersRole sysUsersRole = QSysUsersRole.sysUsersRole;
        List<SysUsersRole> result = jpaQueryFactory
                .select(Projections.bean(
                        SysUsersRole.class,
                        sysRole.roleName,
                        sysRole.guid.as("roleId") ,
                        sysUsersRole.roleId.as("roleState"),
                        sysUsersRole.userId
                ))
                //两种方式左连接表
              /*  .from(sysRole,sysUsersRole)//构建两表笛卡尔集
                .where(sysRole.guid.eq(sysUsersRole.roleId).and(sysUsersRole.userId.eq("20190527160213_BABD6598EF59414D9B70424468DB0753")))//关联两表
               */ .from(sysRole)
                .leftJoin(sysUsersRole)
                .on(sysRole.guid.eq(sysUsersRole.roleId))
                .where(sysUsersRole.userId.eq("20190527160213_BABD6598EF59414D9B70424468DB0753"))
                .fetch();
        return result;
    }
    public List<SysUsersRole> getJPAExpressions() {
        QSysRole sysRole = QSysRole.sysRole;
        QSysUsersRole sysUsersRole = QSysUsersRole.sysUsersRole;
        QSysRoleAuthority sysRoleAuthority = QSysRoleAuthority.sysRoleAuthority;
        QSysAuthority sysAuthority = QSysAuthority.sysAuthority;

        StringPath dd = Expressions.stringPath("dd");
        StringPath ss = Expressions.stringPath("ss");
        MapExpression query = (MapExpression) JPAExpressions.selectDistinct(sysRoleAuthority.authorityId).from(sysRoleAuthority).where(sysRoleAuthority.roleId.in(JPAExpressions.select(sysUsersRole.roleId).from(sysUsersRole).where(sysUsersRole.userId.eq("20190527160213_BABD6598EF59414D9B70424468DB0753"))
        ));
        jpaQueryFactory.select(Projections.bean(
                        SysUsersRole.class,
                        sysRole.roleName,
                        sysRole.guid.as("roleId") ,
                        sysUsersRole.roleId.as("roleState"),
                        sysUsersRole.userId
                ))
                //两种方式左连接表
                /*  .from(sysRole,sysUsersRole)//构建两表笛卡尔集
                  .where(sysRole.guid.eq(sysUsersRole.roleId).and(sysUsersRole.userId.eq("20190527160213_BABD6598EF59414D9B70424468DB0753")))//关联两表
                 */ .from(sysAuthority, (EntityPath<?>) ss)
                .leftJoin(query,dd).on(ss.);



        return null;
    }

}
