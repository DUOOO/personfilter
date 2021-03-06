package com.stalary.personfilter.service.mysql;

import com.stalary.personfilter.data.entity.mysql.Message;
import com.stalary.personfilter.holder.UserHolder;
import com.stalary.personfilter.repo.mysql.MessageRepo;
import com.stalary.personfilter.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MessageService
 *
 * @author lirongqian
 * @since 2018/04/17
 */
@Service
public class MessageService extends BaseService<Message, MessageRepo> {

    MessageService(MessageRepo repo) {
        super(repo);
    }

    @Resource
    private WebSocketService webSocketService;

    public List<Message> findByToId(Long toId) {
        return repo.findByToIdOrderByCreateTimeDesc(toId);
    }

    public List<Message> findByFromId(Long fromId) {
        return repo.findByFromId(fromId);
    }

    /**
     * 查找未读通知的数量
     **/
    public List<Message> findNotRead(Long toId) {
        return repo.findByToIdAndReadState(toId, false);
    }

    public void read(Long id, Long userId) {
        repo.read(id);
        int count = findNotRead(userId).size();
        webSocketService.sendMessage(userId, "" + count);
    }

    public void getCount() {
        Long userId = UserHolder.get().getId();
        int count = findNotRead(userId).size();
        webSocketService.sendMessage(userId, "" + count);
    }
}