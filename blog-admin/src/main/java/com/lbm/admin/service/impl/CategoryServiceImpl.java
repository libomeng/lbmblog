package com.lbm.admin.service.impl;

import com.lbm.admin.entity.Category;
import com.lbm.admin.mapper.CategoryMapper;
import com.lbm.admin.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbm.common.Result;
import com.lbm.common.uitl.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbm
 * @since 2022-01-25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public Result updateCategory(Category category){
        if(ObjectUtil.checkObjFieldIsNull(category)){
            return Result.fail("请正确填写分类信息");
        }
        Integer res = categoryMapper.updateById(category);
        if(res !=1){
            return Result.fail("修改分类失败，请联系管理员小哥");
        }
        return Result.success("分类修改成功");
    }

    @Override
    public Result addCategory(Category category) {
        if(StringUtils.isEmpty(category.getCategoryName())||StringUtils.isEmpty(category.getDescription())){
            return Result.fail("请正确填写分类信息");
        }
        int res = categoryMapper.insert(category);
        if(res !=1){
            return Result.fail("网络拥堵，请稍后再试");
        }
        return Result.success("分类添加成功");
    }
}
