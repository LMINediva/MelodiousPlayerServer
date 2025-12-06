package com.melodiousplayer.controller;

import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器监控
 *
 * @author Mike
 * @date 2025/12/05
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('monitor:server:list')")
    public R getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("server", server);
        return R.ok(resultMap);
    }

}
