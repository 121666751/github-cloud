package cn.spring.mvn.note.web.entity.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.spring.mvn.note.web.entity.NoteBook;
import cn.spring.mvn.note.web.entity.NoteResult;
import cn.spring.mvn.note.web.entity.dao.NoteBookDao;
import cn.spring.mvn.note.web.entity.service.NoteBookService;
import cn.spring.mvn.note.web.util.NoteUtil;

@Service
@Transactional(readOnly = false, rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class NoteBookServiceImpl implements NoteBookService {
	@Resource
	private NoteBookDao bookDao;

	public NoteResult loadBooks(String userId) {
		List<NoteBook> list = bookDao.findByUser(userId);
		NoteResult result = new NoteResult();
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}

	public NoteResult addBook(String bookName, String userId) {
		NoteResult result = new NoteResult();
		// 创建笔记本
		NoteBook book = new NoteBook();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id("5");// normal
		String noteId = NoteUtil.createId();
		book.setCn_notebook_id(noteId);
		Timestamp creatTime = new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(creatTime);
		bookDao.save(book);// 保存笔记本
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(noteId);// 返回笔记本ID
		return result;
	}
}