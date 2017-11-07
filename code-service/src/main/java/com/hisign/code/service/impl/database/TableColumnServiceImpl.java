package com.hisign.code.service.impl.database;

import com.hisign.code.api.business.TableColumnService;
import com.hisign.code.api.business.TranslationDictService;
import com.hisign.code.constant.Constants;
import com.hisign.code.model.database.TableColumn;
import com.hisign.code.model.database.TranslationDict;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.persist.mapper.database.CompanyManageMapper;
import com.hisign.code.persist.mapper.database.TableColumnMapper;
import com.hisign.multiDatabase.ILoadBean;
import com.hisign.trans.model.TransResult;
import com.hisign.trans.util.TransUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字段信息接口实现类
 * @author jiangpeng
 * @since 2017/05/24 17:27
 */
@Service("tableColumnService")
public class TableColumnServiceImpl implements TableColumnService {

    /**
     *字段信息mapper
     */
    public TableColumnMapper tableColumnMapper;

    @Resource
    public CompanyManageMapper companyManageMapper;

    @Resource
    public ILoadBean loadBean;

    @Resource
    public TranslationDictService translationDictService;

    /**
     * 配置日志信息
     */
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public void handleTableList(List<TableColumn> list) throws Exception {
        Set<String> strings = Constants.TRANSLATION_MAP.keySet();
        Set<String> transList = new HashSet<>();
        List<TableColumn> columnList = new ArrayList<>();
        for (TableColumn tableColumn : list) {
            if(StringUtils.isNotEmpty(tableColumn.getComments())) {
                tableColumn.setCommentSource("备注");
            } else {
                if(strings.contains(tableColumn.getColumnName())) {
                    tableColumn.setComments(Constants.TRANSLATION_MAP.get(tableColumn.getColumnName()));
                } else {
                    columnList.add(tableColumn);
                    if(!tableColumn.getColumnName().contains("_") && !strings.contains(tableColumn.getColumnName())) transList.add(tableColumn.getColumnName());
                }
                if(tableColumn.getColumnName().contains("_")) {
                    String[] split = tableColumn.getColumnName().split("_");
                    for (String s : split) {
                        if(!strings.contains(s) && StringUtils.isNotEmpty(s)) transList.add(s);
                    }
                }
                tableColumn.setCommentSource("翻译");
            }
        }
        try {
            List<TransResult> results = TransUtil.transEn2Zh(transList);
            TranslationDict translationDict = new TranslationDict();
            translationDict.setUser(new SysUser("common"));
            if(results != null) {
                for (TransResult result : results) {
                    translationDict.setTranslationDict(result.getSrc(), result.getDst());
                    translationDictService.insertTranslationDict(translationDict);
                }
            }
        } catch (Exception e) {

        }
        //翻译剩余字段
        for (TableColumn tableColumn : columnList) {
            if(strings.contains(tableColumn.getColumnName())) {
                tableColumn.setComments(Constants.TRANSLATION_MAP.get(tableColumn.getColumnName()));
            } else {
                String comment = tableColumn.getColumnName();
                String[] split = tableColumn.getColumnName().split("_");
                for (String s : split) {
                    String str = Constants.TRANSLATION_MAP.get(s);
                    if(comment != null && StringUtils.isNotEmpty(str)) comment.replace(s, str);
                }
                tableColumn.setComments(comment);
            }
        }
    }


    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws Exception
     */
    public List<TableColumn> findTableColumnList(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        List<TableColumn> tableColumnList = tableColumnMapper.findTableColumnList(tableColumn);
        sqlSession.close();
        handleTableList(tableColumnList);
        return tableColumnList;
    }


    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws Exception
     */
    public List<TableColumn> findTableColumnListOld(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        List<TableColumn> tableColumnList = tableColumnMapper.findTableColumnList(tableColumn);
        sqlSession.close();
        //定义翻译工具翻译字段集合
        List<String> orgTextlist = new ArrayList<>();
        //定义单个字段小单词的集合
        List<String> orgTextList2=new ArrayList<>();
        //定义翻译工具信息集合
        List<TableColumn> list = new ArrayList<>();
        //定义拼接的字段信息
        String ort = "";
        //定义翻译信息2
        String transText2="";
        //定义新的字段信息集合来存放字段里面小单词
        List<TableColumn> columnList=new ArrayList<>();
        for (TableColumn column : tableColumnList) {
            //定义词库的译文
            String transText=Constants.TRANSLATION_MAP.get(column.getColumnName());
            //字段描述不为空时
            if(StringUtils.isNotEmpty(column.getComments())){
                column.setCommentSource("备注");
            }else{
                //字段描述为空时的情形
                column.setCommentSource("翻译工具");
                //本次词库不为空时的情形
                if(StringUtils.isNotEmpty(transText)){
                    column.setComments(transText);
                }else {
                    //将源字段信息截取成String类型的数组
                    String word=column.getColumnName();
                    String biaozhi="_";
                    int a=word.indexOf(biaozhi);
                    if(a>=0) {
                        String[] strArray = column.getColumnName().split("\\_");
                        //遍历数组
                        for (int i = 0; i < strArray.length; i++) {
                            //拆分的字段信息本地词库没有的情况
                            if (StringUtils.isEmpty(Constants.TRANSLATION_MAP.get(strArray[i]))) {
                                ort += strArray[i] + "_|_";
                                transText2 += "_";
                            } else {
                                //拆分的字段信息本地词库存在的情形
                                transText2 += Constants.TRANSLATION_MAP.get(strArray[i]);
                            }
                            TableColumn column2 = new TableColumn();
                            //小单词存放入翻译集合2与字段集合2
                            orgTextList2.add(strArray[i]);
                            column2.setColumnName(strArray[i]);
                            columnList.add(column2);
                            orgTextlist.addAll(orgTextList2);
                            list.addAll(columnList);
                        }
                        //截取需要翻译的字段信息的字符串
                        ort = ort.substring(0, ort.length() - 4);
                        //保证翻译的字段与翻译的译文存在于一个字段column中
                        orgTextlist.add(ort);
                        column.setComments(transText2);
                        list.add(column);
                        //清空transText2的缓存并清空ort
                        transText2 = null;
                        ort = null;
                    }else {
                        //不包含字符“_”的源字段的译文本地词库为空的情形
                        if(StringUtils.isEmpty(Constants.TRANSLATION_MAP.get(word))){
                            orgTextlist.add(column.getColumnName());
                            list.add(column);
                        }
                    }
                }
            }
        }
        //定义需要翻译的字段集合2
        List<String> orgTextlist2=new ArrayList<>();
        List<TransResult> transResultList=new ArrayList<>();
        for (int i = 0; i <orgTextlist.size() ; i++) {
            orgTextlist2.add(orgTextlist.get(i));
            //因为考虑到翻译工具一次能翻译的集合字段较少，所以对翻译字段集合批量处理
            if (i % 300==0 || i == orgTextlist.size()-1){
                transResultList.addAll(TransUtil.transEn2Zh(orgTextlist2));
                orgTextlist2.clear();
            }
        }
        for (int i = 0; i <list.size() ; i++) {
            TranslationDict translationDict=new TranslationDict();
            //将译文的属性对应的插入到集合的本来位置
            String dst=transResultList.get(i).getDst();
            String biaozhi2="|";
            int b = dst.indexOf("|");
            if(b>=0){
                String[] dst2=dst.split("\\|");
                for (int j = 0; j <dst2.length ; j++) {
                    transText2=list.get(i).getComments();
                    String[] str=transText2.split("\\_");
                    transText2.replace(str[j],dst2[j]);
                    translationDict.setOriginalText(list.get(i).getColumnName().split("\\_")[j]);
                }
            }else{
                list.get(i).setComments(dst);
                translationDict.setOriginalText(list.get(i).getColumnName());
            }
            //判断本地词库该字段是否存在，如果不存在，则将该信息插入到本地词库
            if (StringUtils.isEmpty(Constants.TRANSLATION_MAP.get(translationDict.getOriginalText()))){
                translationDict.setTranslation(list.get(i).getComments());
                SysUser user=new SysUser();
                user.setUserName("common");
                translationDict.setUser(user);
                translationDictService.insertTranslationDict(translationDict);
            }
            //将翻译的集合插入到字段信息集合
            if(StringUtils.isNotEmpty(list.get(i).getTableName())){
                tableColumnList.add(list.get(i));
            }
        }
        return tableColumnList;
    }

    /**
     * 获取字段信息列表信息数量
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息数量
     * @throws Exception
     */
    public int findTableColumnListForCount(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        int tableColumnListForCount = tableColumnMapper.findTableColumnListForCount(tableColumn);
        sqlSession.close();
        return tableColumnListForCount;
    }

    @Override
    public List<TableColumn> findTableList(TableColumn tableColumn) throws Exception {
        long time1 = System.currentTimeMillis();
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        long time2 = System.currentTimeMillis();
        logger.info("初始化表数据源花费[{}]毫秒", time2 - time1);
        List<TableColumn> tableList = tableColumnMapper.findTableList(tableColumn);
        long time3 = System.currentTimeMillis();
        logger.info("查询表信息花费[{}]毫秒", time3 - time2);
        sqlSession.close();
        return tableList;
    }

    @Override
    public int findTableListForCount(TableColumn tableColumn) throws Exception {
        long time1 = System.currentTimeMillis();
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        long time2 = System.currentTimeMillis();
        logger.info("初始化表数量数据源花费[{}]毫秒", time2 - time1);
        int tableListForCount = tableColumnMapper.findTableListForCount(tableColumn);
        sqlSession.close();
        return tableListForCount;
    }

    /**
     *
     * @param tableColumn
     * @return
     * @throws Exception
     */
    @Override
    public  List<TableColumn> findColumnList(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        tableColumnMapper = sqlSession.getMapper(TableColumnMapper.class);
        List<TableColumn> tableColumnList = tableColumnMapper.findColumnList(tableColumn);
        sqlSession.close();
        return tableColumnList;
    }

    /**
     * 获得数据库连接
     * @param name
     * @return
     * @throws Exception
     */
    public SqlSession getSqlSession(String name) throws Exception {
        if(StringUtils.isEmpty(name)) name = "default";
//        CompanyInfo connectionInfo = companyManageMapper.findConnectionInfoInfo(new CompanyInfo(name));
//        SqlSessionFactory sqlSessionFactory = SqlSessionGetFactory.getConnection(connectionInfo.getSpringBeanInfo(), loadBean, SqlSessionFactory.class);
        return null;
    }

}
