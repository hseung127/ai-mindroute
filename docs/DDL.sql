-- 1. 사용자
CREATE TABLE member (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nickname VARCHAR(50),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 2. 심리검사 정의
CREATE TABLE test (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 3. 문항
CREATE TABLE question (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  test_id BIGINT NOT NULL,
  text TEXT NOT NULL,
  dimension VARCHAR(50),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 4. 선택지
CREATE TABLE choice (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  question_id BIGINT NOT NULL,
  text VARCHAR(200) NOT NULL,
  score_delta INT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 5. 검사 세션
CREATE TABLE test_session (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id CHAR(36) NOT NULL UNIQUE,
  member_id BIGINT,
  test_id BIGINT NOT NULL,
  start_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  finish_date DATETIME,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 6. 응답
CREATE TABLE answer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  choice_id BIGINT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 7. 점수 상태
CREATE TABLE score_state (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  dimension VARCHAR(50) NOT NULL,
  score INT NOT NULL,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 8. 문항 큐
CREATE TABLE queue_item (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  session_id BIGINT NOT NULL,
  question_id BIGINT NOT NULL,
  position INT NOT NULL,
  is_answered BOOLEAN DEFAULT FALSE,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 9. 룰
CREATE TABLE rule (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  test_id BIGINT NOT NULL,
  priority INT NOT NULL,
  stop_after_apply BOOLEAN DEFAULT FALSE,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 10. 룰 조건
CREATE TABLE rule_condition (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  condition_type VARCHAR(50) NOT NULL,
  dimension VARCHAR(50),
  target_dimension VARCHAR(50),
  operator VARCHAR(10) NOT NULL,
  comparison_value INT NOT NULL,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);

-- 11. 룰 액션
CREATE TABLE rule_action (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  rule_id BIGINT NOT NULL,
  action_type VARCHAR(20) NOT NULL,
  question_id BIGINT NOT NULL,
  extra_data TEXT,
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  delete_date DATETIME NULL,
  create_id BIGINT,
  update_id BIGINT,
  delete_id BIGINT
);



-- FK 추가
ALTER TABLE test_session ADD CONSTRAINT fk_test_session_user FOREIGN KEY (member_id) REFERENCES member(id);
ALTER TABLE test_session ADD CONSTRAINT fk_test_session_test FOREIGN KEY (test_id) REFERENCES test(id);

ALTER TABLE question ADD CONSTRAINT fk_question_test FOREIGN KEY (test_id) REFERENCES test(id) ON DELETE CASCADE;
ALTER TABLE choice ADD CONSTRAINT fk_choice_question FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE;

ALTER TABLE answer ADD CONSTRAINT fk_answer_session FOREIGN KEY (session_id) REFERENCES test_session(id) ON DELETE CASCADE;
ALTER TABLE answer ADD CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question(id);
ALTER TABLE answer ADD CONSTRAINT fk_answer_choice FOREIGN KEY (choice_id) REFERENCES choice(id);

ALTER TABLE score_state ADD CONSTRAINT fk_score_state_session FOREIGN KEY (session_id) REFERENCES test_session(id) ON DELETE CASCADE;

ALTER TABLE queue_item ADD CONSTRAINT fk_queue_item_session FOREIGN KEY (session_id) REFERENCES test_session(id) ON DELETE CASCADE;
ALTER TABLE queue_item ADD CONSTRAINT fk_queue_item_question FOREIGN KEY (question_id) REFERENCES question(id);

ALTER TABLE rule ADD CONSTRAINT fk_rule_test FOREIGN KEY (test_id) REFERENCES test(id) ON DELETE CASCADE;

ALTER TABLE rule_condition ADD CONSTRAINT fk_rule_condition_rule FOREIGN KEY (rule_id) REFERENCES rule(id) ON DELETE CASCADE;
ALTER TABLE rule_action ADD CONSTRAINT fk_rule_action_rule FOREIGN KEY (rule_id) REFERENCES rule(id) ON DELETE CASCADE;
ALTER TABLE rule_action ADD CONSTRAINT fk_rule_action_question FOREIGN KEY (question_id) REFERENCES question(id);

