package com.gsm._8th.class4.backend.task12.global.task.info;

import com.gsm._8th.class4.backend.task12.global.annotation.TaskInfo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TaskInfo
public class Task1_2Information {
    @PostConstruct
    public void info_print() {
        log.info("""
                  \n
                  \n
                  _______ _     _
                 |__   __|| |   (_)
                    | |   | |__  _\s
                    | |   | '_ \\| |
                    | |   | | | | |
                    |_|   |_| |_|_|\
                """);
        log.info("\033[34m====================================================================================================\033[0m");
        log.info("\033[36m과제 안내\033[0m");
        log.info("\033[34m====================================================================================================\033[0m");
        log.info("\033[32m섹터: 섹터 1\033[0m");
        log.info("\033[32m과제: 1-2\033[0m");
        log.info("\033[32m요약: JWT 인증/인가 구현\033[0m");
        log.info("\033[33m과제 수행기간: 2025/03/02~2025/03/07\033[0m");
        log.info("\033[35mNotion을 참고하여 과제를 수행해주세요: \033[0m");
        log.info("\033[31m과제를 수행하면서 궁금한 점이나 문제가 발생하면 언제든지 질문해주세요\033[0m");
        log.info("\033[31m과제를 완료하면 Discord 채널에 과제 완료 보고 부탁드립니다\033[0m");
        log.info("\033[36m언제나 화이팅입니다!\033[0m");
        log.info("\033[36m언제나 멋진 개발자가 되기 위해서 오늘도 정진!😎\033[0m");
        log.info("\033[34m====================================================================================================\033[0m");
    }
}