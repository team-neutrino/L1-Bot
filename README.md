# L1-Bot

## CAN FD Bus IDs
| Subsystem | Description | CAN ID | Node Type  |
| --------- | ----------- | ------ | ---------- |
| Swerve    |             | 00-12  |            |
|           | Pigeon 2.0  | 00     | Pigeon 2.0 |
|           | FL CANcoder | 01     | CANcoder   |
|           | FR CANcoder | 02     | CANcoder   |
|           | BL CANcoder | 03     | CANcoder   |
|           | BR CANcoder | 04     | CANcoder   |
|           | FLA         | 05     | Talon FX   |
|           | FLS         | 06     | Talon FX   |
|           | FRA         | 07     | Talon FX   |
|           | FRS         | 08     | Talon FX   |
|           | BLA         | 09     | Talon FX   |
|           | BLS         | 10     | Talon FX   |
|           | BRA         | 11     | Talon FX   |
|           | BRS         | 12     | Talon FX   |


## RoboRIO CAN Bus IDs
| Subsystem | Description | CAN ID | Node Type   |
| --------- | ----------- | ------ | ----------- |
| Robot     |             | 00-01  |             |
|           | RoboRIO     | 00     | RoboRIO     |
|           | PDH         | 01     | PDH         |
| Intake    |             | 02-03  |             |
|           | top roller  | 02     | Talon FX    |
|           | CANandColor | 03     | CANandColor |
| Arm       | arm pivot   | 04     | Talon FX    |

## PDH
| Port | Destination | Breaker | Gauge  |
| ---- | ----------- | ------- | ------ |
| 0    |             | 40 A    | 8 AWG  |
| 1    |             | 40 A    | 8 AWG  |
| 2    |             | 40 A    | 12 AWG |
| 3    |             | 40 A    | 12 AWG |
| 4    |             | 40 A    | 8 AWG  |
| 5    |             | 40 A    | 8 AWG  |
| 6    |             | 30 A    | 14 AWG |
| 7    |             | 20 A    | 18 AWG |
| 8    |             |         |        |
| 9    |             | 10 A    | 22 AWG |
| 10   |             | 30 A    | 14 AWG |
| 11   |             |         |        |
| 12   |             |         |        |
| 13   |             | 40 A    | 12 AWG |
| 14   |             | 40 A    | 12 AWG |
| 15   |             | 40 A    | 12 AWG |
| 16   |             | 40 A    | 12 AWG |
| 17   |             | 40 A    | 12 AWG |
| 18   |             | 40 A    | 8 AWG  |
| 19   |             | 40 A    | 8 AWG  |
| ---- | ----------- | ------- | ------ |
| 20   |             | 3 A     | 18 AWG |
| 21   |             | 10 A    | 22 AWG |
| 22   |             | 20 A    | 18 AWG |
| 23   |             | 10 A    | 18 AWG |

## VRM
| Channel | Destination |
| ------- | ----------- |


## PWM
| Subsystem | Description | Port |
| --------- | ----------- | ---- |

## DIO
| Subsystem | Description | Port |
| --------- | ----------- | ---- |

## Button Map 
<img width="1379" height="722" alt="Screenshot (21)" src="https://github.com/user-attachments/assets/eb1ddd2f-3e9f-4c38-ae68-09779e8e8030" />
